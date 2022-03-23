package com.mobillium.fodamy.core.base

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mobillium.fodamy.Fodamy
import com.mobillium.fodamy.data.network.SafeApiCall
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Named

abstract class BaseViewModel(
    private val repository: BaseRepository,
) : ViewModel() {

    fun showError(networkError: Boolean, errorCode: Int?, errorBody:ResponseBody?){
      //...
    }
}