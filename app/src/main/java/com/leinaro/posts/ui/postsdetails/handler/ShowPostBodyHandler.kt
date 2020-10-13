package com.leinaro.posts.ui.postsdetails.handler

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Either
import com.leinaro.posts.repository.postsdetails.ShowPostBody
import com.leinaro.posts.ui.postsdetails.PostsDetailsFragment
import com.leinaro.posts.utils.ViewHandler
import kotlinx.android.synthetic.main.fragment_posts_details.*

object ShowPostBodyHandler : ViewHandler<ShowPostBody> {
    override fun ShowPostBody.perform(context: Either<Activity, Fragment>) {
        context.map { fragment ->
            fragment as PostsDetailsFragment
            showBody(fragment, this.post.body)
        }
    }

    private fun showBody(fragment: PostsDetailsFragment, body: String) {
        fragment.description_body.text = body
    }
}