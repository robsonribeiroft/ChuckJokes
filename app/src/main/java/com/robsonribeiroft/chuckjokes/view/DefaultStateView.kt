package com.robsonribeiroft.chuckjokes.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.robsonribeiroft.chuckjokes.core.gone
import com.robsonribeiroft.chuckjokes.core.visible
import com.robsonribeiroft.chuckjokes.databinding.DefaultStateViewBinding

class DefaultStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: DefaultStateViewBinding

    init {
        binding = DefaultStateViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setError(throwable: Throwable) = with(binding) {
        showErrorState()
        errorView.text = throwable.message
    }

    fun setError(message: String) = with(binding) {
        showErrorState()
        errorView.text = message
    }

    fun setError(@StringRes stringRes: Int) = with(binding) {
        showErrorState()
        errorView.text = context.getString(stringRes)
    }

    fun setLoading() = with(binding) {
        visible()
        loadingView.visible()
        errorView.gone()
    }

    private fun showErrorState() = with(binding) {
        visible()
        loadingView.gone()
        errorView.visible()
    }

}