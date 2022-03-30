package com.mobillium.fodamy.ui.homepage


import androidx.lifecycle.*
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val authRepository: AuthRepository
)  : BaseViewModel(){

    private var isUserLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun logout() =
        viewModelScope.launch {
           isUserLogin.value.let{ isUserLogin->
                if (isUserLogin == true){
                    when (val response = authRepository.logout()) {
                        is Resource.Success -> {
                            navigate(HomepageFragmentDirections.actionHomepageFragmentToLoginFragment())
                        }
                        is Resource.Failure -> {
                            response.errorBody?.let { showError(it) }
                        }
                        is Resource.Loading -> {
                            showLoading()
                        }
                    }
                }else{
                    navigate(HomepageFragmentDirections.actionHomepageFragmentToLoginFragment())
                }
        }
    }
}
