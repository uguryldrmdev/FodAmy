package com.mobillium.fodamy.ui.recipes

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.lastadded.LastAddedPagingSource
import com.mobillium.fodamy.data.repository.LastAddedRepository
import com.mobillium.fodamy.data.responses.editorchoices.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: LastAddedRepository
) : BaseViewModel() {

    val list =
        Pager(PagingConfig(pageSize = 1)){
            LastAddedPagingSource(repository)
        }.liveData.cachedIn(viewModelScope)

    fun getRecipe(recipe: Recipe){
        //..
    }

}
