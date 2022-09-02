package com.robsonribeiroft.chuckjokes.base_presentation

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText{
    class StringDynamic(val value: String): UiText()
    class StringResource(@StringRes val value: Int, vararg val args: Any): UiText()
}

fun UiText.getText(context: Context): String {
    return when(this) {
        is UiText.StringDynamic -> this.value
        is UiText.StringResource -> context.getString(this.value, this.args)
    }
}

