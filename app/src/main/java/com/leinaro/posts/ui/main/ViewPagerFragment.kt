package com.leinaro.posts.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.leinaro.posts.R
import com.leinaro.posts.repository.main.MainViewDataState
import com.leinaro.posts.utils.ViewHandler
import com.leinaro.posts.utils.handleViewData
import kotlinx.android.synthetic.main.fragment_view_pager.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ViewPagerFragment : Fragment() {

    private val TAB_TITLES = arrayOf(
        R.string.tab_all,
        R.string.tab_favorites
    )

    private val mainViewModel: MainViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //<editor-fold desc="lifecycle">
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        setViewPagerAdapter()
        setFloatButtonAction()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh_action_menu -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private fun setViewPagerAdapter() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, TAB_TITLES)
        view_pager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setFloatButtonAction() {
        delete_all.setOnClickListener { view ->
            Snackbar.make(view, "Delete all?", Snackbar.LENGTH_LONG)
                .setAction("Delete", deleteAll).show()
        }
    }

    private fun setObservers() {
        mainViewModel.getViewData().observe(this.viewLifecycleOwner, Observer {
            handleViewData(it.first, it.second as ViewHandler<MainViewDataState>)
        })
    }

    private val deleteAll: View.OnClickListener = View.OnClickListener {
        //TODO: viewModel.deleteAll()
    }
    //</editor-fold>

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}