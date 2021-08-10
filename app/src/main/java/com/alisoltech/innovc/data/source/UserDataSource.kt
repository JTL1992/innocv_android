package com.alisoltech.innovc.data.source

import com.alisoltech.innovc.data.models.User

interface UserDataSource {

    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()
    }

    interface LoadUserCallback {

        fun onUserLoaded(users: User)

        fun onDataNotAvailable()
    }

    interface LoadDataCallback {

        fun onRequestSuccess()

        fun onRequestFail()
    }

    fun loadUsers(callback: LoadUsersCallback)

    fun loadUser(userId: Int, callback: LoadUserCallback)

    fun saveUser(user: User, callback: LoadDataCallback)

    fun deleteUser(userId: Int, callback: LoadDataCallback)

    fun editUser(user: User, callback: LoadDataCallback)
}