package com.example.PostsAPI_jan10.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.PostsAPI_jan10.databinding.RowItemPostBinding
import com.example.PostsAPI_jan10.model.network.models.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val postsList = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            RowItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postsList[position])
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun submitList(posts: List<Post>) {
        postsList.clear()
        postsList.addAll(posts)
        notifyDataSetChanged()
    }

    class PostViewHolder(
        private val binding: RowItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding) {
            userIdTv.text = post.userId.toString()
            idTv.text = post.id.toString()
            titleTv.text = post.title
            bodyTv.text = post.body
        }
    }
}