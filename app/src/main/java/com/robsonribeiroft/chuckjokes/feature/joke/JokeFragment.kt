package com.robsonribeiroft.chuckjokes.feature.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robsonribeiroft.chuckjokes.presentation.MainViewModel
import com.robsonribeiroft.chuckjokes.core.gone
import com.robsonribeiroft.chuckjokes.core.onPostValue
import com.robsonribeiroft.chuckjokes.core.visible
import com.robsonribeiroft.chuckjokes.databinding.FragmentJokeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JokeFragment: Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        viewModel.getJoke(arguments?.getString("category"))
    }

    private fun setupViews() = with(binding) {
        textJoke.gone()
        stateView.setLoading()
    }

    private fun setupObservers() {
        viewModel.joke.onPostValue(
            viewLifecycleOwner,
            loading = {
                binding.textJoke.gone()
                binding.stateView.setLoading()
            },
            onError = {
                binding.textJoke.gone()
                binding.stateView.setError(it)
            },
            onSuccess = { joke ->
                binding.stateView.gone()
                binding.textJoke.visible()
                binding.textJoke.text = joke
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}