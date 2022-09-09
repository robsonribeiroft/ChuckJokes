package com.robsonribeiroft.chuckjokes.feature.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.robsonribeiroft.chuckjokes.R
import com.robsonribeiroft.chuckjokes.base_presentation.UiText
import com.robsonribeiroft.chuckjokes.base_presentation.getText
import com.robsonribeiroft.chuckjokes.core.gone
import com.robsonribeiroft.chuckjokes.core.visible
import com.robsonribeiroft.chuckjokes.databinding.FragmentCategoriesBinding
import com.robsonribeiroft.chuckjokes.feature.model.CategoriesBindingModel
import com.robsonribeiroft.chuckjokes.presentation.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment: Fragment() {

    private val viewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val adapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter.apply {
            onItemClicked = { categorySelected ->
                val directions = CategoriesFragmentDirections.actionCategoriesFragmentToJokeFragment(categorySelected)
                findNavController().navigate(directions)
            }
        }

        setupObservers()
        viewModel.getCategories()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.categories.collectLatest { categoriesState ->
                categoriesState.stateHandler(
                    loading = {
                        binding.recyclerView.gone()
                        binding.stateView.setLoading()
                    },
                    onError = { uiText: UiText, _: CategoriesBindingModel ->
                        binding.recyclerView.gone()
                        binding.stateView.setError(uiText.getText(this@CategoriesFragment.requireContext()))
                    },
                    onSuccess = { categoriesBindingModel ->
                        binding.recyclerView.visible()
                        binding.stateView.gone()
                        adapter.submitList(categoriesBindingModel.categories)
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}