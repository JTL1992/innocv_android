package com.alisoltech.innovc.di

import com.alisoltech.innovc.data.source.UserDataSource
import com.alisoltech.innovc.data.source.UserRepository
import com.alisoltech.innovc.data.source.remote.RemoteUserDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun remoteUserSource(source: RemoteUserDataSource): UserDataSource

//    @Binds
//    abstract fun userRepository(userRepository: UserRepository): UserRepository

}