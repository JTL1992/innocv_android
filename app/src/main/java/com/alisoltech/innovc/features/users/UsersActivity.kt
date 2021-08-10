package com.alisoltech.innovc.features.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alisoltech.innovc.R
import com.alisoltech.innovc.data.source.UserRepository
import com.alisoltech.innovc.data.source.remote.RemoteUserDataSource
import com.alisoltech.innovc.utils.replaceFragmentInActivity
import com.alisoltech.innovc.utils.setupActionBar

class UsersActivity : AppCompatActivity() {

    private lateinit var userPresenter: UserPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        // Set up the toolbar.
        setupActionBar(R.id.toolbar) {
            setDisplayShowHomeEnabled(true)
        }
        val usersFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as UsersFragment? ?: UsersFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        userPresenter = UserPresenter(UserRepository(RemoteUserDataSource()), usersFragment)
    }
}