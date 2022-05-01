package com.celis.ceibaapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celis.ceibaapplication.databinding.ViewUserBinding
import com.celis.ceibaapplication.persistence.model.User

class UsersRvAdapter(private val usersList: ArrayList<User>):
    RecyclerView.Adapter<UsersRvAdapter.UserViewHolder>(){

    interface UserCallback{
        fun showUserPublications(user: User)
    }

    lateinit var userCallback: UserCallback

    class UserViewHolder(
        private val binding: ViewUserBinding,
        private val userCallback: UserCallback): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User) {
            binding.apply {
                tvUsername.text = user.name
                tvUserPhone.text = user.phone
                tvUserEmail.text = user.email
                btnShowUserPublications.setOnClickListener {
                    userCallback.showUserPublications(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ViewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false), userCallback)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = usersList.size
}