package com.alisoltech.innovc.di

import com.alisoltech.innovc.features.users.UsersActivity
import com.alisoltech.innovc.features.users.UsersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun usersActivity(): UsersActivity
}