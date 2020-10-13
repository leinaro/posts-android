package com.leinaro.posts.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.leinaro.posts.R
import com.leinaro.posts.repository.main.MainViewDataState
import com.leinaro.posts.utils.ViewHandler
import com.leinaro.posts.utils.handleViewData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAB_TITLES = arrayOf(
        R.string.tab_all,
        R.string.tab_favorites
    )

    private val mainViewModel: MainViewModel by viewModels()

    //<editor-fold desc="lifecycle">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setViewPagerAdapter()
        setFloatButtonAction()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh_action_menu -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private fun setViewPagerAdapter(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, TAB_TITLES)
        view_pager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setFloatButtonAction(){
        findViewById<FloatingActionButton>(R.id.delete_all).setOnClickListener { view ->
            Snackbar.make(view, "Delete all?", Snackbar.LENGTH_LONG)
                .setAction("Delete", deleteAll).show()
        }
    }

    private fun setObservers(){
        mainViewModel.getViewData().observe(this, Observer {
            handleViewData(it.first, it.second as ViewHandler<MainViewDataState>)
        })
    }

    private val deleteAll: View.OnClickListener = View.OnClickListener {
        //TODO: viewModel.deleteAll()
    }
    //</editor-fold>
}