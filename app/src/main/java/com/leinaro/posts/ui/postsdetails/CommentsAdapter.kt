package com.leinaro.posts.ui.postsdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Comment

class CommentsAdapter(private var comments: Array<Comment>) :
    RecyclerView.Adapter<CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item, parent, false) as ConstraintLayout
        return CommentsViewHolder(view)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.textView.text = comments[position].body
    }

    fun setDataSet(comments: List<Comment>) {
        this.comments = comments.toTypedArray()
    }
}
