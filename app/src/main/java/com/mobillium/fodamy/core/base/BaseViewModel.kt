package com.mobillium.fodamy.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

abstract class BaseViewModel() : ViewModel() {

    private val _channel = Channel<BaseViewEvent> {}
    val eventFlow = _channel.receiveAsFlow()

    private fun sendEvent(event: BaseViewEvent) {
        viewModelScope.launch {
            _channel.send(event)
        }
    }

    fun navigate(direction: NavDirections) {
        sendEvent(BaseViewEvent.Navigate(direction))
    }

    fun showError(errorBody: ResponseBody){
        sendEvent(BaseViewEvent.ShowError(errorBody))
    }

    fun showLoading(){
        sendEvent(BaseViewEvent.ShowLoading)
    }

    fun showToast(stringID: Int){
        sendEvent(BaseViewEvent.ShowToast(stringID))
    }

}

