package com.bojanvilic.crvenazvezdainfo.util

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.function.Function

@OptIn(FlowPreview::class)
abstract class NetworkBoundResource<RemoteType, LocalType>(private val networkStatusTracker: NetworkStatusTracker) {

    open fun shouldFetchFromRemote(): Boolean = true
    abstract fun getRemote(): suspend () -> RemoteType
    abstract fun getLocal(): Flow<LocalType>
    abstract suspend fun saveCallResult(remoteType: RemoteType)
    abstract fun mapper(): Function<RemoteType, LocalType>

    fun asFlow(): Flow<Resource<LocalType>> = flow {
        val result = if (shouldFetchFromRemote()) {
            emit(Resource.Loading(getLocal().first()))
            try {
                Timber.d("PROBA: ovde")
                saveCallResult(getRemote().invoke())
                getLocal().map {
                    Resource.Success(it)
                }
            } catch (throwable: Throwable) {
                Timber.d("PROBA: error: ${throwable.message}")
                getLocal().map {
                    Resource.Error(it, throwable)
                }
            }
        } else {
            Timber.d("PROBA: ovde 2")
            getLocal().map { Resource.Success(it) }
        }
        emitAll(result)
    }.retryWhen { _, attempt ->
        attempt < 3 || networkStatusTracker.isNetworkAvailable.first()
    }
}
