package com.mobillium.fodamy.ui.homepage.editorchoices

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentEditorChoicesBinding
import com.mobillium.fodamy.ext.setDivider
import com.mobillium.fodamy.ui.homepage.editorchoices.adapter.EditorChoicesAdapterPaging
import com.mobillium.fodamy.ui.homepage.lastadded.adapter.LastAddedAdapterPaging


class EditorChoicesFragment : BaseFragment<FragmentEditorChoicesBinding, EditorChoicesViewModel>(R.layout.fragment_editor_choices) {

    private lateinit var mEditorChoicesAdapterPaging : EditorChoicesAdapterPaging

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
            layoutManager = LinearLayoutManager(activity)
            EditorChoicesAdapterPaging{ recipe, isUserClicked ->

                //Değişecek denemedir
                if (!isUserClicked){
                    Toast.makeText(requireContext(),recipe.title, Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(),recipe.user.username, Toast.LENGTH_SHORT).show()
                }

            }.also { mEditorChoicesAdapterPaging = it }
            adapter = mEditorChoicesAdapterPaging
            setDivider(R.drawable.recycle_view_divider)
        }
    }
    private fun createDataList(){

        viewModel.list.observe(viewLifecycleOwner){
            mEditorChoicesAdapterPaging.submitData(lifecycle,it)
        }
    }

}