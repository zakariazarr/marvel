package com.mrtwoza.marvel.utils.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T> onBackground(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Default, block)

suspend fun <T> onMain(block: suspend (CoroutineScope.() -> T)) = withContext(Dispatchers.Main, block)