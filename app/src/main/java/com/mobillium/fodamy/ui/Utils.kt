package com.mobillium.fodamy.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.ui.auth.login.LoginFragment

fun<A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also {
        it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
}
fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}
fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.networkError -> requireView().snackbar(
            "İnternet Bağlantınızı Kontrol Edin",
            retry
        )
        failure.errorCode == 400 -> {
            if (this is LoginFragment) {
                requireView().snackbar("Lütfen gerekli alanları doldurunuz")
            }
        }
        failure.errorCode == 403 -> {
            if (this is LoginFragment) {
                requireView().snackbar("Girdiğiniz bilgiler yanlış")
            }
        }
        else -> {
            val error = failure.errorBody?.source().toString()
            requireView().snackbar(error)
        }
    }
}