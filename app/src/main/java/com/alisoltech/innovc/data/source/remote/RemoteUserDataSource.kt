package com.alisoltech.innovc.data.source.remote

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.network.api.UsersDataLoader
import com.alisoltech.innovc.data.source.UserDataSource

class RemoteUserDataSource: UserDataSource {

    private val remoteSource: UserDataSource = UsersDataLoader()

    override fun loadUsers(callback: UserDataSource.LoadUsersCallback) {
        remoteSource.loadUsers(callback)
    }

    override fun loadUser(userId: Int, callback: UserDataSource.LoadUserCallback) {
        remoteSource.loadUser(userId, callback)
    }

    override fun saveUser(user: User, callback: UserDataSource.LoadDataCallback) {
        remoteSource.saveUser(user, callback)
    }

    override fun deleteUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        remoteSource.deleteUser(userId, callback)
    }

    override fun editUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        remoteSource.editUser(userId, callback)
    }
}