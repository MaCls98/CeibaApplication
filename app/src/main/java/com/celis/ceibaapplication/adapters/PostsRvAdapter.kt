package com.celis.ceibaapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celis.ceibaapplication.databinding.ViewPostBinding
import com.celis.ceibaapplication.persistence.model.Post

class PostsRvAdapter(private val postList: List<Post>):
    RecyclerView.Adapter<PostsRvAdapter.PostViewHolder>(){

    class PostViewHolder(private val binding: ViewPostBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.apply {
                tvPostTitle.text = post.title
                tvPostBody.text = post.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(ViewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = postList.size
}