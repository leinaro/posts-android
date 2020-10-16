package com.leinaro.posts.ui.postsdetails.handler

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Either
import com.leinaro.posts.repository.postsdetails.ShowFavorite
import com.leinaro.posts.ui.postsdetails.PostsDetailsFragment
import com.leinaro.posts.utils.ViewHandler

object ShowFavoriteHandler : ViewHandler<ShowFavorite> {
    override fun ShowFavorite.perform(context: Either<Activity, Fragment>) {
        context.map { fragment ->
            fragment as PostsDetailsFragment
            showFavorite(fragment, this.isFavorite)
        }
    }

    private fun showFavorite(fragment: PostsDetailsFragment, isFavorite: Boolean) {
        if (isFavorite) {
            fragment.showAddToFavoriteIcon()
        } else {
            fragment.showRemoveToFavoriteIcon()
        }
    }
}