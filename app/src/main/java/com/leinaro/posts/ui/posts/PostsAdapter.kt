package com.leinaro.posts.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Posts


class PostsAdapter(private var postDataset: MutableList<Posts>) :
    RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.posts_item, parent, false
        ) as ConstraintLayout
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.textView.text = postDataset[position].body
        val posts = postDataset[position]
        holder.view.setOnClickListener(onPostClickListener(posts, holder))
        holder.favorite.visibility = if (posts.isFavorite) View.VISIBLE else View.GONE

        if (position < 20 && !posts.isRead)
            holder.identifier.visibility = View.VISIBLE
        else
            holder.identifier.visibility = View.GONE
    }

    override fun getItemCount() = postDataset.size

    fun setDataSet(postsList: List<Posts>) {
        postDataset = postsList.toMutableList()
    }

    fun deleteItem(position: Int, viewHolder: RecyclerView.ViewHolder) {
        //val deletedPost = postDataset[position]
        //val lastdeletedposition = position
        postDataset.removeAt(position)
        notifyItemRemoved(position)
        showUndoSnackbar(viewHolder)
    }

    private fun showUndoSnackbar(viewHolder: RecyclerView.ViewHolder) {

        val view: View = viewHolder.itemView.rootView
        val snackbar: Snackbar = Snackbar.make(
            view, "Undo revome post?",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("UNDO") { v -> undoDelete() }
        snackbar.show()
    }

    private fun undoDelete() {
        /*mListItems.add(
            mRecentlyDeletedItemPosition,
            mRecentlyDeletedItem
        )
        notifyItemInserted(mRecentlyDeletedItemPosition)*/
    }

    private fun onPostClickListener(posts: Posts, holder: PostsViewHolder) = View.OnClickListener {
        posts.isRead = true
        holder.identifier.visibility = View.GONE
        val bundle = bundleOf()
        bundle.putSerializable("posts", posts)
        holder.view.findNavController().navigate(
            R.id.action_PostsFragment_to_PostsDetailsFragment,
            bundle
        )
    }
}
