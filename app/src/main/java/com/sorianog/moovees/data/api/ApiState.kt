package com.sorianog.moovees.data.api

sealed class ApiState<T> {
    class Loading<T> : ApiState<T>()
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error<T>(val error: Any) : ApiState<T>()
}