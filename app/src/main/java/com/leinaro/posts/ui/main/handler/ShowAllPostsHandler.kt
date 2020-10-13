package com.leinaro.posts.ui.main.handler

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Either
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.main.ShowAllPosts
import com.leinaro.posts.ui.main.ViewPagerFragment
import com.leinaro.posts.ui.posts.PostsFragment
import com.leinaro.posts.utils.ViewHandler

object ShowAllPostsHandler : ViewHandler<ShowAllPosts> {

    override fun ShowAllPosts.perform(context: Either<Activity, Fragment>) {
        context.map { fragment ->
            fragment as ViewPagerFragment
            setViewPagerAdapter(fragment, this.posts)
        }
    }

    private fun setViewPagerAdapter(fragment: ViewPagerFragment, postsList: List<Posts>) {
        val allPostsFragment =
            fragment.childFragmentManager.findFragmentByTag("f" + 0) as? PostsFragment
        allPostsFragment?.setPosts(postsList)
    }
}