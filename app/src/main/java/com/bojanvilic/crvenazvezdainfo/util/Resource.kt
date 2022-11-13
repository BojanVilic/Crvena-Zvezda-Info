package com.bojanvilic.crvenazvezdainfo.util

sealed class Resource<T>(
    val data: T?,
    val error: Throwable? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Loading<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, throwable: Throwable): Resource<T>(data, throwable)
}
