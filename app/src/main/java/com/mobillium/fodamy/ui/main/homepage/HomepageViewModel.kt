package com.mobillium.fodamy.ui.main.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.preferences.PreferencesManager
import com.mobillium.fodamy.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private var preferencesManager: PreferencesManager
) : BaseViewModel() {

    val token = MutableLiveData(preferencesManager.token)

    fun logout() =
        viewModelScope.launch {

            when (val response = authRepository.logout()) {
                is Resource.Success -> {
                   navigate(HomepageFragmentDirections.actionHomepageFragmentToLoginFragment())
                }
                is Resource.Failure -> {
                    navigate(HomepageFragmentDirections.actionHomepageFragmentToLoginFragment()) //Silinmesi lazım
                    response.errorBody?.let { showError(it) }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }

        }

}