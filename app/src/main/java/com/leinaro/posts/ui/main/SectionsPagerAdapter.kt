package com.leinaro.posts.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leinaro.posts.ui.posts.PostsFragment

class SectionsPagerAdapter(private val context: Fragment, private val tabTitles: Array<Int>) :
    FragmentStateAdapter(context) {

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