package com.leinaro.posts.ui.postsdetails.handler

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Either
import com.leinaro.posts.datasources.remote.Comment
import com.leinaro.posts.datasources.remote.User
import com.leinaro.posts.repository.postsdetails.ShowPostDetails
import com.leinaro.posts.ui.postsdetails.PostsDetailsFragment
import com.leinaro.posts.utils.ViewHandler
import kotlinx.android.synthetic.main.fragment_posts_details.*

object ShowPostDestilsHandler : ViewHandler<ShowPostDetails> {
    override fun ShowPostDetails.perform(context: Either<Activity, Fragment>) {
        context.map { fragment ->
            fragment as PostsDetailsFragment
            showUser(fragment, this.data.first)
            showComments(fragment, this.data.second)
        }
    }

    private fun showUser(fragment: PostsDetailsFragment, user: User) {
        fragment.usermail_body.text = user.email
        fragment.username_body.text = user.name
        fragment.userphone_body.text = user.phone
        fragment.userwebsite_body.text = user.website
    }

    private fun showComments(fragment: PostsDetailsFragment, comments: List<Comment>) {
        fragment.setComments(comments)
    }
}