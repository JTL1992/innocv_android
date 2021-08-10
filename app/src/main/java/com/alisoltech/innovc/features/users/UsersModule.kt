package com.alisoltech.innovc.features.users

import com.alisoltech.innovc.di.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UsersModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun usersFragment(): UsersFragment

    @Binds
    abstract fun presenter(presenter: UserPresenter): UserContract.Presenter


}