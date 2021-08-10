package com.alisoltech.innovc.features.users

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.source.UserDataSource
import com.alisoltech.innovc.data.source.UserRepository

class UserPresenter(val userRepository: UserRepository, val view: UserContract.View): UserContract.Presenter {

    init {
        view.presenter = this
    }

    override fun loadUsers() {
        view.setLoadingIndicator(true)
        userRepository.loadUsers(object :UserDataSource.LoadUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                if (!view.isActive) {
                    return
                }
                view.showUsers(users)
                view.setLoadingIndicator(false)
            }

            override fun onDataNotAvailable() {
                if (!view.isActive) {
                    return
                }
                view.showLoadingUserError()
                view.setLoadingIndicator(false)
            }

        })
    }

    override fun addNewUser() {
        view.showAddUser()
    }

    override fun start() {
        loadUsers()
    }
}