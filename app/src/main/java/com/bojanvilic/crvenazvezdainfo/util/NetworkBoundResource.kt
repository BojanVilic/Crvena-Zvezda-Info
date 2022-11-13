package com.bojanvilic.crvenazvezdainfo.util

import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.function.Function

abstract class NetworkBoundResource<RemoteType, LocalType>(
    networkStatusTracker: NetworkStatusTracker,
    emitter: FlowCollector<Resource<LocalType>>
) {
    open fun shouldFetchFromRemote(): Boolean = true
    abstract fun getRemote(): suspend () -> RemoteType
    abstract fun getLocal(): Flow<LocalType>
    abstract fun saveCallResult(localType: LocalType)
    abstract fun mapper(): Function<RemoteType, LocalType>

    init {
        Timber.d("PROBA: inicijalizovano")
        flow<LocalType> {
            emitter.emit(test().single())
        }
        flow {
            if (shouldFetchFromRemote()) {
                emitter.emit(Resource.Loading(getLocal().first()))
                try {
                    Timber.d("PROBA: ovde")
                    saveCallResult(mapper().apply(getRemote().invoke()))
                    emit(Resource.Success(getLocal()))
                } catch (throwable: Throwable) {
                    Timber.d("PROBA: error")
                    emitter.emit(Resource.Error(throwable = throwable))
                }
            } else {
                Timber.d("PROBA: ovde 2")
                emit(Resource.Success(getLocal().first()))
            }
        }.retryWhen { _, attempt ->
            attempt < 3 || networkStatusTracker.isNetworkAvailable.first()
        }
    }
}