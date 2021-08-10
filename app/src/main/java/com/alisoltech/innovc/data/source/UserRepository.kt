package com.alisoltech.innovc.data.source

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.di.RemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(var userDataSource: UserDataSource): UserDataSource {

    override fun loadUsers(callback: UserDataSource.LoadUsersCallback) {
        userDataSource.loadUsers(callback)
    }

    override fun loadUser(userId: Int, callback: UserDataSource.LoadUserCallback) {
        userDataSource.loadUser(userId, callback)
    }

    override fun saveUser(user: User, callback: UserDataSource.LoadDataCallback) {
        userDataSource.saveUser(user, callback)
    }

    override fun deleteUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        userDataSource.deleteUser(userId, callback)
    }

    override fun editUser(user: User, callback: UserDataSource.LoadDataCallback) {
        userDataSource.editUser(user, callback)
    }
}