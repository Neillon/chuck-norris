package com.example.chuck_norris.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.favorites.databinding.FragmentFavoritesBinding
import com.example.chuck_norris.favorites.di.FavoritesModule
import com.example.chuck_norris.favorites.ui.adapter.FavoriteJokeItemClick
import com.example.chuck_norris.favorites.ui.adapter.FavoritesAdapter
import com.example.chuck_norris.favorites.ui.data.FavoritesViewEvent
import com.example.chuck_norris.ui.JokeUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

class FavoritesFragment : Fragment(), FavoriteJokeItemClick {

    private lateinit var binding: FragmentFavoritesBinding
    private val navController by lazy { findNavController() }
    private val viewModel: FavoritesViewModel by viewModel()

    private val favoritesAdapter = FavoritesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
        viewModel.processEvent(FavoritesViewEvent.LoadFavoriteJokes)
    }

    /**
     * Inject all dependencies for favorites feature
     */
    private fun injectDependencies() {
        koinApplication {
            unloadKoinModules(FavoritesModule.dependencies)
            loadKoinModules(FavoritesModule.dependencies)
        }
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {
        binding.recyclerViewFavoriteJokes.adapter = favoritesAdapter
        binding.recyclerViewFavoriteJokes.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Observe the ViewState
     */
    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            binding.bottomInformationViewFavorites.isVisible = viewState.isLoading

            favoritesAdapter.insertData(viewState.jokes)

            viewState.error?.let {
                binding.bottomInformationViewFavorites.makeError(viewState.error!!)
            }

        })
    }

    override fun onItemClick(joke: JokeUI) {
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToJokeDetailFragment(joke = joke)
        navController.navigate(action)
    }
}