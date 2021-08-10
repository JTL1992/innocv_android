package com.alisoltech.innovc.features.users

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alisoltech.innovc.R
import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.di.ActivityScope
import com.alisoltech.innovc.di.FragmentScope
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.fragment_users_list.view.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */

@ActivityScope
class UsersFragment @Inject constructor() : DaggerFragment(), UserContract.View {
    @Inject
    lateinit var presenter: UserContract.Presenter

    private val userAdapter = UserRecyclerViewAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_users_list, container, false)
        val recyclerView = root.fragmentUserListRecyclerView
        root.fragmentUserListRefreshLayout.setOnRefreshListener {
            presenter.loadUsers()
        }
        requireActivity().fab_add_user.setOnClickListener {
            //TODO
        }
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }
        }

        setHasOptionsMenu(true)
        return root
    }
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.users_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> showFilterMenu()
        }
        return true
    }

    override fun setLoadingIndicator(active: Boolean) {
        val root = view ?: return
        root.fragmentUserListRefreshLayout.apply {
            post { isRefreshing = active }
        }
    }

    override fun showUsers(users: List<User>) {
        userAdapter.users = users
    }

    override fun showAddUser() {
        TODO("Not yet implemented")
    }

    override fun showUserDetail(userId: String) {
        TODO("Not yet implemented")
    }

    override fun showLoadingUserError() {
        showMessage(getString(R.string.loading_user_error))
    }

    private fun showMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showFilterMenu() {
        val activity = activity ?: return
        val context = context ?: return
        PopupMenu(context, activity.findViewById(R.id.menu_filter)).apply {
            menuInflater.inflate(R.menu.filter_users, menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.name -> {}
                    R.id.birthday -> {}
                    else -> {}
                }
                presenter.loadUsers()
                true
            }
            show()
        }
    }
}