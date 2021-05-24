package com.mrtwoza.marvel.core.base

sealed class Response<out ResultT> {
    data class Success<out ResultT>(
            val result: ResultT
    ) : Response<ResultT>()

    data class Error(
            val exception: Exception
    ) : Response<Nothing>()

    companion object {
        fun <ResultT> success(result: ResultT) = Success(result)
        fun error(exception: Exception) = Error(exception)
    }
}

inline fun <reified ResultT> Response<ResultT>.fold(
        onError: (Exception) -> Unit,
        onSuccess: (ResultT) -> Unit
) {
    when (this) {
        is Response.Success -> onSuccess(result)
        is Response.Error -> onError(exception)
    }
}