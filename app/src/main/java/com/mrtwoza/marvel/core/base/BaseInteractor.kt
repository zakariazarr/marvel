package com.mrtwoza.marvel.core.base

import android.util.Log
import com.mrtwoza.marvel.utils.coroutines.onBackground
import com.mrtwoza.marvel.utils.coroutines.onMain

abstract class BaseInteractor<in RequestT, out ResultT> {

    suspend fun execute(request: RequestT): Response<ResultT> = onMain {
        Log.d(BaseInteractor::class.java.name, "execute(request = $request)")
        try {
            val response = onBackground {
                doJob(request)
            }
            Response.success(response).also { success ->
                Log.v(BaseInteractor::class.java.name, "successful execution: ${success.result}")
            }
        } catch (e: Exception) {
            Response.error(e).also { failure ->
                Log.w(BaseInteractor::class.java.name,  "execution failed", failure.exception)
            }
        }
    }

    protected abstract suspend fun doJob(request: RequestT): ResultT
}