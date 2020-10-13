package com.leinaro.posts.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Posts

class PostsAdapter(private var postDataset: Array<Posts>) :
    RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_item, parent, false) as ConstraintLayout
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.textView.text = postDataset[position].body
        val posts = postDataset[position]
        holder.view.setOnClickListener{
            posts.isRead = true
            holder.identifier.visibility = View.INVISIBLE
            val bundle = bundleOf("posts" to "")
            holder.view.findNavController().navigate(
                R.id.action_PostsFragment_to_PostsDetailsFragment,
                bundle
            )
        }
        if (position < 20 && !posts.isRead)
            holder.identifier.visibility = View.VISIBLE
        else
            holder.identifier.visibility = View.INVISIBLE
    }

    override fun getItemCount() = postDataset.size

    fun setDataSet(postsList: List<Posts>){
        postDataset = postsList.toTypedArray()
    }
}
