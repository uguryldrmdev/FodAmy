package com.mobillium.fodamy.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentRecipesBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.homepage.lastadded.adapter.LastAddedAdapterPaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding,RecipesViewModel>(R.layout.fragment_recipes){

    private lateinit var mLastAddedAdapterPaging : LastAddedAdapterPaging

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initEditorChoicesRecycleView()
        createDataList()
    }
    private fun initEditorChoicesRecycleView(){

        binding.recycleViewRecipes.apply {
            layoutManager = LinearLayoutManager(activity)
            LastAddedAdapterPaging{ recipe,isUserClicked ->

                //Değişecek denemedir
                if (!isUserClicked){
                    Toast.makeText(requireContext(),recipe.title, Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(),recipe.user.username, Toast.LENGTH_SHORT).show()
                }

            }.also { mLastAddedAdapterPaging = it }
            adapter = mLastAddedAdapterPaging
            setDivider(R.drawable.recycle_view_divider)
        }
    }
    private fun createDataList(){

        viewModel.list.observe(viewLifecycleOwner){
            mLastAddedAdapterPaging.submitData(lifecycle,it)
        }
    }
}