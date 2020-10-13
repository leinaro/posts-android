package com.leinaro.posts.ui.main

import android.content.Context
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leinaro.posts.ui.posts.PostsFragment
import com.leinaro.posts.R
import com.leinaro.posts.datasources.remote.Posts

class SectionsPagerAdapter(private val context: FragmentActivity, private val tabTitles: Array<Int>) : FragmentStateAdapter(context) {

    override fun getItemCount(): Int {
        return tabTitles.size
    }

    override fun createFragment(position: Int): Fragment {
        return PostsFragment.newInstance(
            context.resources.getString(tabTitles[position]),
            position + 1
        )
    }
}