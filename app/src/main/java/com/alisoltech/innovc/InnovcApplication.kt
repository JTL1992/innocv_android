package com.alisoltech.innovc

import com.alisoltech.innovc.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class InnovcApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}