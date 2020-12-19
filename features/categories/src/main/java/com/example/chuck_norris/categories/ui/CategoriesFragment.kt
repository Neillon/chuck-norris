package com.example.chuck_norris.categories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuck_norris.categories.databinding.FragmentCategoriesBinding
import com.example.chuck_norris.categories.ui.adapter.CategoriesAdapter
import com.example.chuck_norris.categories.ui.data.CategoriesViewEvent

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoriesAdapter by lazy { CategoriesAdapter() }
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        viewModel.processEvent(CategoriesViewEvent.LoadCategories)
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {
        setupRecyclerViewCategories()
        setupSwipeRefreshLayout()
    }

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

}