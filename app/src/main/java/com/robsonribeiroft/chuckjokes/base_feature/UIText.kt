package com.robsonribeiroft.chuckjokes.base_feature

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.robsonribeiroft.chuckjokes.base_feature.UIText.DynamicString
import com.robsonribeiroft.chuckjokes.base_feature.UIText.ResourceString

sealed class UIText {
    data class DynamicString(val value: String? = null): UIText()
    class ResourceString(@StringRes val resourceId: Int, vararg val args: Any): UIText()
}

fun UIText.asString(context: Context): String {
    return when(this) {
        is DynamicString -> value ?: emptyString()
        is ResourceString -> context.getString(resourceId, *args)
    }
}

internal fun emptyString() = ""


fun Fragment.getString(uiText: UIText): String {
    return uiText.asString(this.requireContext())
}
