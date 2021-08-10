package com.alisoltech.innovc.features.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alisoltech.innovc.R
import com.alisoltech.innovc.data.source.UserRepository
import com.alisoltech.innovc.data.source.remote.RemoteUserDataSource
import com.alisoltech.innovc.di.ActivityScope
import com.alisoltech.innovc.utils.replaceFragmentInActivity
import com.alisoltech.innovc.utils.setupActionBar
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@ActivityScope
class UsersActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var userPresenter: UserPresenter

    @Inject
    lateinit var usersFragmentProvider: Lazy<UsersFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        // Set up the toolbar.
        setupActionBar(R.id.toolbar) {
            setDisplayShowHomeEnabled(true)
        }
        val usersFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as UsersFragment? ?: usersFragmentProvider.get().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

    }
}