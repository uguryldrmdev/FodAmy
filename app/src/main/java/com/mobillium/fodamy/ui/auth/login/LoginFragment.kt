package com.mobillium.fodamy.ui.auth.login


import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentLoginBinding
import com.mobillium.fodamy.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>(R.layout.fragment_login)