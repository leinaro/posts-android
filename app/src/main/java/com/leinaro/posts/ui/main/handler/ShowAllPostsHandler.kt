package com.leinaro.posts.ui.main.handler

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.findFragment
import arrow.core.Either
import com.google.android.material.tabs.TabLayoutMediator
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.main.ShowAllPosts
import com.leinaro.posts.ui.main.MainActivity
import com.leinaro.posts.ui.main.SectionsPagerAdapter
import com.leinaro.posts.ui.posts.PostsFragment
import com.leinaro.posts.utils.ViewHandler
import kotlinx.android.synthetic.main.activity_main.*

object ShowAllPostsHandler : ViewHandler<ShowAllPosts> {

    override fun ShowAllPosts.perform(context: Either<Activity, Fragment>) {
        context.map {
            // No fragment implementation
        }
        context.mapLeft { activity ->
            activity as MainActivity
            setViewPagerAdapter(activity, this.posts)
        }
    }

    private fun setViewPagerAdapter(mainActivity: MainActivity, postsList: List<Posts>){
        val allPostsFragment = mainActivity.supportFragmentManager.findFragmentByTag("f" + 0) as? PostsFragment
        allPostsFragment?.setPosts(postsList)
    }
}