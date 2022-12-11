package com.example.data.thread

import kotlinx.coroutines.CoroutineDispatcher

interface ThreadContract {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}