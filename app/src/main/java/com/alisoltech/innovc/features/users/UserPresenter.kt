package com.alisoltech.innovc.features.users

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.source.UserDataSource
import com.alisoltech.innovc.data.source.UserRepository
import com.alisoltech.innovc.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class UserPresenter @Inject constructor(): UserContract.Presenter {
    @Inject
    lateinit var userRepository: UserRepository

    var view: UserContract.View? = null

    override fun loadUsers() {
        view?.setLoadingIndicator(true)
        userRepository.loadUsers(object :UserDataSource.LoadUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                view?.let {
                    it.showUsers(users)
                    it.setLoadingIndicator(false)
                }
            }

            override fun onDataNotAvailable() {
                view?.showLoadingUserError()
                view?.setLoadingIndicator(false)
            }

        })
    }

    override fun addNewUser() {
        view?.showAddUser()
    }

    override fun takeView(view: UserContract.View) {
        this.view = view
        loadUsers()
    }

    override fun dropView() {
        this.view = null
    }
}