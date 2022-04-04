package com.mobillium.fodamy.ui.homepage.editorchoices

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentEditorChoicesBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.homepage.editorchoices.adapter.EditorChoicesAdapterPaging


class EditorChoicesFragment : BaseFragment<FragmentEditorChoicesBinding, EditorChoicesViewModel>(R.layout.fragment_editor_choices) {

    private var mEditorChoicesAdapterPaging = EditorChoicesAdapterPaging()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initEditorChoicesRecycleView()
        createDataList()
    }
    private fun initEditorChoicesRecycleView(){

        binding.recycleViewEditorChoices.apply {
            adapter = mEditorChoicesAdapterPaging
            layoutManager = LinearLayoutManager(activity)
        }.setDivider(R.drawable.recycle_view_divider)
    }
    private fun createDataList(){

        viewModel.list.observe(viewLifecycleOwner){
            mEditorChoicesAdapterPaging.submitData(lifecycle,it)
        }
    }

}