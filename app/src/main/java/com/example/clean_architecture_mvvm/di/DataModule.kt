package com.example.clean_architecture_mvvm.di

import com.example.data.repository.LoginRepository
import com.example.data.repository.UsersRepository
import com.example.domain.repository.UsersContract
import com.example.domain.repository.LoginContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLoginContract(loginRepository: LoginRepository): LoginContract

    @Binds
    abstract fun bindUsersContract(usersRepository: UsersRepository): UsersContract
}