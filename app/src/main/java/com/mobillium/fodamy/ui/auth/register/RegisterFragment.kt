package com.mobillium.fodamy.ui.auth.register

import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentRegisterBinding
import com.mobillium.fodamy.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding, AuthViewModel>(R.layout.fragment_register)
