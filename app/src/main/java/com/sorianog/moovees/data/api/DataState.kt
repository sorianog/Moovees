package com.sorianog.moovees.data.api

sealed class DataState<T> {
    class Loading<T> : DataState<T>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val error: Any) : DataState<T>()
}