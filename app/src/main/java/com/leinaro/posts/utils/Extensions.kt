package com.leinaro.posts.utils

import android.app.Activity
import arrow.core.Left

fun <ViewData> Activity.handleViewData(viewData: ViewData, handler: ViewHandler<ViewData>){
    handler.run { viewData.perform(Left(this@handleViewData)) }
 }