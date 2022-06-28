package com.mobillium.fodamy.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.BR
import com.mobillium.fodamy.R
import com.mobillium.fodamy.ext.findGenericSuperclass
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {

    private var _binding: VB? = null
    lateinit var viewModel: VM

    var viewModelClass: Class<VM> =
        findGenericSuperclass<BaseFragment<VB, VM>>()
            ?.actualTypeArguments
            ?.getOrNull(1) as? Class<VM>
            ?: throw IllegalStateException()

    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        if (_binding == null)
            throw IllegalArgumentException(getString(R.string.binding_null_error))
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(viewModelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvents()
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.eventFlow.collect {
                when (it) {
                    is BaseViewEvent.Navigate -> {
                        findNavController().navigate(it.direction)
                    }
                    is BaseViewEvent.ShowError -> {
                        Toast.makeText(requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is BaseViewEvent.ShowLoading -> {
                        //...
                    }
                    is BaseViewEvent.ShowToast -> {
                        Toast.makeText(requireContext(), getString(it.stringID),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}