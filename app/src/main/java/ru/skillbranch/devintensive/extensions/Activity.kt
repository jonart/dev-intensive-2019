package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = findViewById<View>(android.R.id.content)
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = this.convertDpToPx(50F)

    return heightDiff > marginOfError
}

fun Context.convertDpToPx(dp: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )

fun Activity.isKeyboardClosed():Boolean = this.isKeyboardOpen().not()