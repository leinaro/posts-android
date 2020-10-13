package com.leinaro.posts.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import arrow.core.Either

typealias ViewDataState<T> = Pair<T, ViewHandler<*>>

interface ViewHandler<ViewData> {
    fun ViewData.perform(context: Either<Activity, Fragment>)
}
