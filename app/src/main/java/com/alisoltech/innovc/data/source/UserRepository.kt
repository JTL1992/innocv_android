package com.alisoltech.innovc.data.source

import com.alisoltech.innovc.data.models.User

class UserRepository(val userDataSource: UserDataSource): UserDataSource {

    override fun loadUsers(callback: UserDataSource.LoadUsersCallback) {
        userDataSource.loadUsers(callback)
    }

    override fun loadUser(userId: String, callback: UserDataSource.LoadUserCallback) {
        userDataSource.loadUser(userId, callback)
    }

    override fun saveUser(user: User, callback: UserDataSource.LoadDataCallback) {
        userDataSource.saveUser(user, callback)
    }

    override fun deleteUser(user: User, callback: UserDataSource.LoadDataCallback) {
        userDataSource.deleteUser(user, callback)
    }

    override fun editUser(userId: Int, callback: UserDataSource.LoadDataCallback) {
        userDataSource.editUser(userId, callback)
    }
}