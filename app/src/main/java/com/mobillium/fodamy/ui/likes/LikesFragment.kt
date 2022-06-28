package com.mobillium.fodamy.ui.likes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.data.responses.likes.Category
import com.mobillium.fodamy.databinding.FragmentLikesBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.likes.adapter.LikesCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikesFragment : BaseFragment<FragmentLikesBinding,LikesViewModel>(R.layout.fragment_likes){
    private var mCategoryList: ArrayList<Category>?=null
    private lateinit var mCategoryListAdapter: LikesCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initCategoriesRecycleView()
        createCategoryList()
    }

    private fun initCategoriesRecycleView(){
        binding.recycleViewLikesCategories.apply {
            layoutManager = LinearLayoutManager(activity)
            LikesCategoryAdapter{ category ->
                //Değişecek
                Toast.makeText(requireContext(),category.name,Toast.LENGTH_SHORT).show()
            }.also { mCategoryListAdapter = it }
            adapter = mCategoryListAdapter
            setDivider(R.drawable.recycle_view_divider)
        }
    }
    private fun createCategoryList(){
        viewModel.getCategoryListDataObserver().observe(viewLifecycleOwner) {
            mCategoryList = it
            mCategoryListAdapter.setCategoryListData(it)
        }

        viewModel.getCategories(){
            //...
        }
    }
}