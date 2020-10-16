package com.leinaro.posts.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.ui.main.PageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*


/**
 * The fragment argument representing the section name for this fragment.
 */
private const val ARG_SECTION_NAME = "section_name"

/**
 * The fragment argument representing the section number for this fragment.
 */
private const val ARG_SECTION_NUMBER = "section_number"

/**
 * A fragment containing a recyclerview with posts.
 */
@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var viewAdapter: PostsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val pageViewModel: PageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageViewModel.setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)

        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(this.context)

        viewAdapter = PostsAdapter(mutableListOf())

        this.posts_list.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = viewManager
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = viewAdapter

            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(viewAdapter, this.context))
            itemTouchHelper.attachToRecyclerView(posts_list)
        }
    }

    fun setPosts(postsList: List<Posts>) {
        viewAdapter.setDataSet(postsList)
        viewAdapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section name and section number.
         */
        @JvmStatic
        fun newInstance(sectionName: String, sectionNumber: Int): PostsFragment {
            return PostsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION_NAME, sectionName)
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}