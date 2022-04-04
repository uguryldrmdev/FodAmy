package com.mobillium.fodamy.data.network.lastadded

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.EditorChoiceRepository
import com.mobillium.fodamy.data.repository.LastAddedRepository
import com.mobillium.fodamy.data.responses.editorchoices.Recipe
import java.lang.Exception
import javax.inject.Inject

class LastAddedPagingSource@Inject constructor(val repository: LastAddedRepository) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val page = params.key?:1

        return try {
            val response = repository.getLastAdded(page)
            var dataList = ArrayList<Recipe>()
            var lastItem = 0
            var currentPage = 1
            var perPage = 0

            when(response) {
                is Resource.Success -> {
                    dataList = response.value.recipe
                    lastItem = response.value.pagination.last_item
                    currentPage = response.value.pagination.current_page
                    perPage = response.value.pagination.per_page
                }
                is Resource.Failure -> {
                    response.errorBody
                }
                is Resource.Loading -> {

                }
            }
            LoadResult.Page(
                data = dataList,
                prevKey = if (page == 1) null else page-1,
                nextKey = if ((lastItem/currentPage) <= perPage) null else page+1,
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}