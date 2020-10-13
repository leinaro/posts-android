package com.leinaro.posts.ui.postsdetails

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsViewHolder(view: ConstraintLayout) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.comment_body
}
