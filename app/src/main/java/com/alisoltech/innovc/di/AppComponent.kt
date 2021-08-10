package com.alisoltech.innovc.di

import android.app.Application
import com.alisoltech.innovc.InnovcApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<InnovcApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder
        fun build(): AppComponent
    }
}