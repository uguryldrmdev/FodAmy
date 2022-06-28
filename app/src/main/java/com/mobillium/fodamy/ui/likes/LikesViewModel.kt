package com.mobillium.fodamy.ui.likes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.data.repository.LikesRepository
import com.mobillium.fodamy.data.responses.likes.Category
import com.mobillium.fodamy.ui.auth.login.LoginFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(
    private val repository: LikesRepository
) : BaseViewModel() {

    var mCategoryList: MutableLiveData<ArrayList<Category>> = MutableLiveData()

    fun getCategoryListDataObserver(): MutableLiveData<ArrayList<Category>> {
        return mCategoryList
    }

    fun getCategories(function: () -> Unit) {
        viewModelScope.launch {
            when (val response = repository.getLikes()) {
                is Resource.Success -> {
                    mCategoryList.postValue(response.value.categoryList)
                }
                is Resource.Failure -> { response.errorBody?.let { showError(it) } }
                is Resource.Loading -> { showLoading()}
            }
        }
    }
}

