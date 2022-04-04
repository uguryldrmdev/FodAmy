package com.mobillium.fodamy.ui.homepage.editorchoices

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.network.editorchoices.EditorChoicesPagingSource
import com.mobillium.fodamy.data.repository.EditorChoiceRepository
import com.mobillium.fodamy.data.responses.editorchoices.Data
import com.mobillium.fodamy.data.responses.editorchoices.Pagination
import com.mobillium.fodamy.ui.auth.login.LoginFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditorChoicesViewModel  @Inject constructor(
    private val repository: EditorChoiceRepository
) : BaseViewModel() {

    val list =
        Pager(PagingConfig(pageSize = 1)){
            EditorChoicesPagingSource(repository)
        }.liveData.cachedIn(viewModelScope)

}
