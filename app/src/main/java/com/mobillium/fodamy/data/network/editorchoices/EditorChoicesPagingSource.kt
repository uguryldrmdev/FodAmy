package com.mobillium.fodamy.data.network.editorchoices

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.EditorChoiceRepository
import com.mobillium.fodamy.data.responses.editorchoices.Data
import java.lang.Exception
import javax.inject.Inject

class EditorChoicesPagingSource @Inject constructor(val repository: EditorChoiceRepository) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
       return state.anchorPosition?.let {
           val anchorPage = state?.closestPageToPosition(it)
           anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key?:1

        return try {
            val response = repository.getEditorChoices(page)
            var dataList = ArrayList<Data>()

            when(response) {
                is Resource.Success -> {
                    dataList = response.value.data
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
                    nextKey = if (dataList.isEmpty()) null else page+1,
                )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}