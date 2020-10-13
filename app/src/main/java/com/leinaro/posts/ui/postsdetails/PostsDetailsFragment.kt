package com.leinaro.posts.ui.postsdetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Comment
import com.leinaro.posts.repository.postsdetails.PostsDetailsViewDataState
import com.leinaro.posts.ui.main.MainActivity
import com.leinaro.posts.utils.ViewHandler
import com.leinaro.posts.utils.handleViewData
import kotlinx.android.synthetic.main.fragment_posts_details.*
import kotlinx.android.synthetic.main.fragment_view_pager.toolbar

class PostsDetailsFragment : Fragment() {

    private val viewModel: PostsDetailsViewModel by viewModels()

    private lateinit var viewAdapter: CommentsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        setCommetsAdapter()
        setObservers()
        setArguments()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_posts_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    //<editor-fold desc="private methods">
    private fun setObservers() {
        viewModel.getViewData().observe(this.viewLifecycleOwner, Observer {
            handleViewData(it.first, it.second as ViewHandler<PostsDetailsViewDataState>)
        })
    }

    private fun setArguments() {
        viewModel.setArgument(arguments)
    }

    private fun setCommetsAdapter(){
        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CommentsAdapter(arrayOf())
        this.comments_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = viewAdapter
        }
    }

    fun setComments(comments: List<Comment>) {
        viewAdapter.setDataSet(comments)
        viewAdapter.notifyDataSetChanged()
    }
    //</editor-fold>
}