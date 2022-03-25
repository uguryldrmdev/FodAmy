package com.mobillium.fodamy.core.base

import androidx.navigation.NavDirections
import okhttp3.ResponseBody

/**
 * @author Mert Gölcü
 * @date 23.03.2022
 */

sealed class BaseViewEvent {
    data class Navigate(val direction: NavDirections) : BaseViewEvent()

    data class ShowError(val errorBody: ResponseBody) : BaseViewEvent()

    object ShowLoading : BaseViewEvent()

    data class ShowToast(val stringID:Int) : BaseViewEvent()
}