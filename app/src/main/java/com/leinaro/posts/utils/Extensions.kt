package com.leinaro.posts.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Left
import arrow.core.Right
import com.leinaro.posts.datasources.local.Post
import com.leinaro.posts.datasources.remote.Posts

fun <ViewData> Activity.handleViewData(viewData: ViewData, handler: ViewHandler<ViewData>) {
    handler.run { viewData.perform(Left(this@handleViewData)) }
}

fun <ViewData> Fragment.handleViewData(viewData: ViewData, handler: ViewHandler<ViewData>) {
    handler.run { viewData.perform(Right(this@handleViewData)) }
}

fun Post.toRemotePost(): Posts {
    return Posts(
        userId = this.userId,
        id = this.id,
        body = this.body,
        title = this.title
    )
}

fun Posts.toLocalPost(): Post {
    return Post(
        userId = this.userId,
        id = this.id,
        body = this.body,
        title = this.title
    )
}