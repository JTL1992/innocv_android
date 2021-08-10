package com.alisoltech.innovc.features.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.databinding.FragmentUsersBinding


class UserRecyclerViewAdapter(users: List<User>) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    var users: List<User> = users
        @SuppressLint("NotifyDataSetChanged")
        set(users) {
            field = users
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.nameTV.text = item.nameData
        holder.birthdayTV.text = item.birthdayData
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(binding: FragmentUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTV: TextView = binding.usersFragmentNameTV
        val birthdayTV: TextView = binding.usersFragmentBirthdayTV

        override fun toString(): String {
            return super.toString() + " '" + nameTV.text + "'"
        }
    }

}