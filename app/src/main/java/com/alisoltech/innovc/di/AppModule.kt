package com.alisoltech.innovc.di

import android.app.Application
import android.content.Context
import com.alisoltech.innovc.data.network.ApiClientManager
import com.alisoltech.innovc.data.source.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

//    @Binds
//    abstract fun bindApiClientManager(manager: ApiClientManager): ApiClientManager

}