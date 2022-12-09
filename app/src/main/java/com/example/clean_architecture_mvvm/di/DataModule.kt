package com.example.clean_architecture_mvvm.di

import com.example.data.LoginRepository
import com.example.domain.contracts.LoginContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLoginContract(loginRepository: LoginRepository): LoginContract
}