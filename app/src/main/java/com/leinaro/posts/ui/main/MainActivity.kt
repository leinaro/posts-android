package com.leinaro.posts.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leinaro.posts.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //<editor-fold desc="lifecycle">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //</editor-fold>
}