package com.mobillium.fodamy.core.base

import androidx.navigation.NavDirections

/**
 * @author Mert Gölcü
 * @date 23.03.2022
 */

sealed class BaseViewEvent {
    data class Navigate(val direction: NavDirections) : BaseViewEvent()
}