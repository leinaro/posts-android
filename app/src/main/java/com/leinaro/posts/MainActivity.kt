package com.leinaro.posts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.leinaro.posts.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //<editor-fold desc="lifecycle">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setViewPagerAdapter()
        setFloatButtonAction()

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
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    private fun setFloatButtonAction(){
        findViewById<FloatingActionButton>(R.id.delete_all).setOnClickListener { view ->
            Snackbar.make(view, "Delete all?", Snackbar.LENGTH_LONG)
                .setAction("Delete", deleteAll).show()
        }
    }

    private val deleteAll: View.OnClickListener = View.OnClickListener {
        //TODO: viewModel.deleteAll()
    }
    //</editor-fold>
}