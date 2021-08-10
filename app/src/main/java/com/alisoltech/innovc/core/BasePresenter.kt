package com.alisoltech.innovc.core

interface BasePresenter<T> {

    fun takeView(view: T)
    fun dropView()
}