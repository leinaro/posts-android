package com.leinaro.posts.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import arrow.core.Left
import arrow.core.Right

fun <ViewData> Activity.handleViewData(viewData: ViewData, handler: ViewHandler<ViewData>){
    handler.run { viewData.perform(Left(this@handleViewData)) }
}

fun <ViewData> Fragment.handleViewData(viewData: ViewData, handler: ViewHandler<ViewData>){
    handler.run { viewData.perform(Right(this@handleViewData)) }
}