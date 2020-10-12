package com.leinaro.posts.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R

class PostsAdapter(private val postDataset: Array<String>) :
    RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_item, parent, false) as ConstraintLayout
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.textView.text = postDataset[position]
    }

    override fun getItemCount() = postDataset.size
}
