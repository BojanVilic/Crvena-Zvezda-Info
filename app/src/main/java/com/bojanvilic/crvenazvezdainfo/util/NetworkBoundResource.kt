package com.bojanvilic.crvenazvezdainfo.util

import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.function.Function

abstract class NetworkBoundResource<RemoteType, LocalType>(private val networkStatusTracker: NetworkStatusTracker) {

    open suspend fun shouldFetchFromRemote(): suspend () -> Boolean = { true }
    abstract fun getRemote(): suspend () -> RemoteType
    abstract fun getLocal(): Flow<LocalType>
    abstract suspend fun saveCallResult(data: LocalType)
    abstract fun mapper(): Function<RemoteType, LocalType>

    fun asFlow(): Flow<Resource<LocalType>> = flow {
        val result = if (shouldFetchFromRemote().invoke()) {
            emit(Resource.loading(getLocal().first()))
            try {
                saveCallResult(mapper().apply(getRemote().invoke()))
                getLocal().map {
                    Resource.success(it)
                }
            } catch (throwable: Throwable) {
                Timber.e(throwable.message)
                getLocal().map { Resource.error(it, throwable) }
            }
        } else {
            getLocal().map { Resource.success(it) }
        }
        emitAll(result)
    }
}
