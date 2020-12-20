package com.example.chuck_norris.categories.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuck_norris.R
import com.example.chuck_norris.categories.databinding.FragmentCategoriesBinding
import com.example.chuck_norris.categories.di.CategoriesModule
import com.example.chuck_norris.categories.ui.adapter.CategoriesAdapter
import com.example.chuck_norris.categories.ui.data.CategoriesViewEffect
import com.example.chuck_norris.categories.ui.data.CategoriesViewEvent
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.network.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoriesAdapter by lazy { CategoriesAdapter() }
    private val viewModel: CategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDepenencies()

        setupViews()
        observeViewState()
        observeViewEffect()

        viewModel.processEvent(CategoriesViewEvent.LoadCategories)
    }

    /**
     * Inject the feature dependencies
     */
    private fun injectDepenencies() {
        koinApplication {
            unloadKoinModules(CategoriesModule.dependencies)
            loadKoinModules(CategoriesModule.dependencies)
        }
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {
        setupRecyclerViewCategories()
        setupSwipeRefreshLayout()
    }

    //region Views
    /**
     * Setup the Swipe Refresh Layout to respond refresh the list
     */
    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayoutCategories.setOnRefreshListener {
            viewModel.processEvent(CategoriesViewEvent.RefreshCategories)
            binding.swipeRefreshLayoutCategories.isRefreshing = false
        }
    }

    /**
     * Setup the recyclerView for the categories
     */
    private fun setupRecyclerViewCategories() {
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCategories.adapter = categoriesAdapter
    }

    /**
     * Setup ViewState Observer
     */
    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            categoriesAdapter.clearData()
            categoriesAdapter.insertData(viewState.categories!!)
            binding.bottomInformationViewCategories.isVisible = viewState.isLoading
        })
    }

    /**
     * Setup ViewEffect Observer
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun observeViewEffect() {
        viewModel.viewEffect.observe(viewLifecycleOwner, Observer { viewEffect ->
            when (viewEffect) {
                is CategoriesViewEffect.ShowError -> {
                    binding.bottomInformationViewCategories.makeError(viewEffect.message)
                }
            }.exhaustive
        })
    }

    //endregion
}