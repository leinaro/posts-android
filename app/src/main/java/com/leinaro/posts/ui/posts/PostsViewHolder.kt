package com.leinaro.posts.ui.posts

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R
import kotlinx.android.synthetic.main.posts_item.view.*

class PostsViewHolder(val view: ConstraintLayout) : RecyclerView.ViewHolder(view){
    val textView : TextView = view.text_view
}