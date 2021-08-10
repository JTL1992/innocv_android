package com.alisoltech.innovc.features.users

import com.alisoltech.innovc.core.BasePresenter
import com.alisoltech.innovc.core.BaseView
import com.alisoltech.innovc.data.models.User

interface UserContract {

    interface View: BaseView<Presenter> {

        var isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showUsers(users: List<User>)

        fun showAddUser()

        fun showUserDetail(userId: String)

        fun showLoadingUserError()

        fun showFilterMenu()
    }

    interface Presenter: BasePresenter {

        fun loadUsers()

        fun addNewUser()

    }
}