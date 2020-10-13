package com.leinaro.posts.ui.posts

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.posts_item.view.*

class PostsViewHolder(val view: ConstraintLayout) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.text_view
    val identifier: ImageView = view.new_identifier
}