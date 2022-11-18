package com.bojanvilic.crvenazvezdainfo.util

class Resource<out T>(
    var status: Status?,
    val data: T,
    val throwable: Throwable? = null) {
    companion object {

        fun <T> loading(data: T): Resource<T> {
            return Resource(Status.LOADING, data)
        }

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(data: T, throwable: Throwable?): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }

        fun <X> newResource(status: Status?, data: X, throwable: Throwable?): Resource<X> {
            return Resource(status, data, throwable)
        }
    }
}
