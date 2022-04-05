package com.mobillium.fodamy.ui.homepage.lastadded

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentLastAddedBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.homepage.lastadded.adapter.LastAddedAdapterPaging


class LastAddedFragment : BaseFragment<FragmentLastAddedBinding, LastAddedViewModel>(R.layout.fragment_last_added) {

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

        binding.recycleViewLastAdded.apply {
            layoutManager = LinearLayoutManager(activity)
            LastAddedAdapterPaging{ recipe,isUserClicked ->

                //Değişecek denemedir
                if (!isUserClicked){
                    Toast.makeText(requireContext(),recipe.title,Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(),recipe.user.username,Toast.LENGTH_SHORT).show()
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