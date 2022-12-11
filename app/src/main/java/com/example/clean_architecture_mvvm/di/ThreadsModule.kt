package com.example.clean_architecture_mvvm.di

import com.example.data.thread.ThreadContract
import com.example.data.thread.ThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ThreadsModule {

    @Binds
    abstract fun bindThreadsContract(threadImpl: ThreadImpl) : ThreadContract
}