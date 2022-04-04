package com.mobillium.fodamy.ui.homepage.lastadded

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentLastAddedBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.homepage.editorchoices.adapter.EditorChoicesAdapterPaging
import com.mobillium.fodamy.ui.homepage.lastadded.adapter.LastAddedAdapterPaging


class LastAddedFragment : BaseFragment<FragmentLastAddedBinding, LastAddedViewModel>(R.layout.fragment_last_added) {

    private var mLastAddedAdapterPaging = LastAddedAdapterPaging()

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
            adapter = mLastAddedAdapterPaging
            layoutManager = LinearLayoutManager(activity)
        }.setDivider(R.drawable.recycle_view_divider)
    }
    private fun createDataList(){

        viewModel.list.observe(viewLifecycleOwner){
            mLastAddedAdapterPaging.submitData(lifecycle,it)
        }
    }
}