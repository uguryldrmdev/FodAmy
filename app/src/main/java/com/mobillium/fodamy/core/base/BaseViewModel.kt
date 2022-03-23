package com.mobillium.fodamy.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel() : ViewModel() {

    //suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }

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

}

