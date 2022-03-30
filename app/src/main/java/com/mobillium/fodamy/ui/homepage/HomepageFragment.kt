package com.mobillium.fodamy.ui.homepage

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentHomepageBinding
import com.mobillium.fodamy.ui.homepage.adapter.HomepageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageFragment : BaseFragment<FragmentHomepageBinding, HomepageViewModel>(R.layout.fragment_homepage){

    private lateinit var viewPagerAdapter: HomepageAdapter
    private var tabItems = ArrayList<String>()
    var isUserLogin: Boolean?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      initialize()
    }

    private fun initialize(){
        initTabItems()
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager(){
        viewPagerAdapter = HomepageAdapter(this)

        viewPagerAdapter.addFragmentList()

        binding.viewPagerHomepage.adapter = viewPagerAdapter
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayoutHomepage,binding.viewPagerHomepage){
                tab,position ->
            tab.text = tabItems[position]
        }.attach()
    }

    private fun initTabItems(){
        tabItems.add(getString(R.string.editors_choices))
        tabItems.add(getString(R.string.last_added))
    }
}