package com.alisoltech.innovc.data.network.api

import android.annotation.SuppressLint
import android.util.Log
import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.network.ApiClientManager
import com.alisoltech.innovc.data.network.DataLoader
import com.alisoltech.innovc.data.source.UserDataSource

class UsersDataLoader : DataLoader(), UserDataSource {

    @SuppressLint("CheckResult")
    override fun loadUsers(callback: UserDataSource.LoadUsersCallback) {
        observe(
            ApiClientManager().create(UsersServices.GetUsers::class.java).getUsers()
        ).subscribe(
            { models: List<User>? ->
                models?.let {
                    callback.onUsersLoaded(models)
                }
            }, { error ->
                error.message?.let {
                    Log.e("#error#", it)
                    callback.onDataNotAvailable()
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun loadUser(userId: Int, callback: UserDataSource.LoadUserCallback) {
        observe(
            ApiClientManager().create(UsersServices.GetUser::class.java).getUser(userId)
        ).subscribe(
            { model: User? ->
                model?.let {
                    callback.onUserLoaded(model)
                }
            }, { error ->
                error.message?.let {
                    callback.onDataNotAvailable()
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun saveUser(user: User, callback: UserDataSource.LoadDataCallback) {
        observe(
            ApiClientManager().create(UsersServices.PostUser::class.java).postUser(user)
        ).subscribe(
            {
                callback.onRequestSuccess()
            }, { error ->
                error.message?.let {
                    callback.onRequestFail()
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun deleteUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        observe(
            ApiClientManager().create(UsersServices.DeleteUser::class.java).deleteUser(userId)
        ).subscribe(
            {
                callback.onRequestSuccess()
            }, { error ->
                error.message?.let {
                    callback.onRequestFail()
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun editUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        observe(
            ApiClientManager().create(UsersServices.PutUser::class.java).putUser(userId)
        ).subscribe(
            {
                callback.onRequestSuccess()
            }, { error ->
                error.message?.let {
                    callback.onRequestFail()
                }
            })
    }
}