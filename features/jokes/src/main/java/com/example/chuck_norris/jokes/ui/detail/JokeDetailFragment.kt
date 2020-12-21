package com.example.chuck_norris.jokes.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.databinding.FragmentJokeDetailBinding
import com.example.chuck_norris.jokes.di.JokesModule
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEffect
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEvent
import com.example.chuck_norris.ui.CategoryUI
import com.example.chuck_norris.ui.JokeUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication
import timber.log.Timber

class JokeDetailFragment : Fragment() {

    private lateinit var binding: FragmentJokeDetailBinding
    private val navController by lazy { findNavController() }
    private val arguments: JokeDetailFragmentArgs by navArgs()

    private val viewModel: JokeDetailViewModel by viewModel()
    private lateinit var currentJoke: JokeUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments.joke != null) {
            loadJokeFromArguments()
        } else {
            loadJokeFromInternet(arguments.category!!)
        }

        setupViews()
        observeViewState()
        observeViewEffect()
    }

    /**
     * Initializes the graph of dependencies
     */
    private fun injectDependencies() {
        koinApplication {
            unloadKoinModules(JokesModule.dependencies)
            loadKoinModules(JokesModule.dependencies)
        }
    }

    /**
     * Observe View State
     */
    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            // Bottom Info
            binding.bottomInformationViewJokeDetail.isLoading = viewState.isLoadingJoke
            binding.bottomInformationViewJokeDetail.isVisible = viewState.isLoadingJoke

            if (viewState.joke != null) {
                currentJoke = viewState.joke

                // Description
                binding.textViewJokeDescription.text = viewState.joke.value
                binding.textViewJokeDescription.isVisible = viewState.joke.value.isNotEmpty()

                // Button Web
                binding.buttonShowJokeOnline.isVisible = viewState.joke.url.isNotEmpty()
                viewState.joke.url.takeIf { it.isNotEmpty() }.run {
                    binding.buttonShowJokeOnline.setOnClickListener {
                        viewModel.processEvent(
                            JokeDetailViewEvent.OpenJokeOnBrowser(viewState.joke.url)
                        )
                    }
                }

                // Image icon
                binding.imageViewJokeIcon.isVisible = viewState.joke.iconUrl.isNotEmpty()
                viewState.joke.iconUrl.takeIf { it.isNotEmpty() }.run {
                    binding.imageViewJokeIcon.load(this) {
                        crossfade(true)
                        placeholder(R.drawable.ic_image_outline)
                        transformations(RoundedCornersTransformation(8f))
                    }
                }
            } else {
                binding.textViewJokeDescription.isVisible = false
                binding.buttonShowJokeOnline.isVisible = false
                binding.imageViewJokeIcon.isVisible = false
            }

            viewState.favoritingJoke.takeIf { it }?.run {
                binding.bottomInformationViewJokeDetail.isVisible = true
                binding.bottomInformationViewJokeDetail.isLoading = false
                binding.bottomInformationViewJokeDetail.information = getString(R.string.favoriting_joke)
            }

            viewState.error?.let { message ->
                binding.bottomInformationViewJokeDetail.makeError(message!!)
            }
        })
    }


    /**
     * ObserveViewEffect
     */
    private fun observeViewEffect() {
        viewModel.viewEffect.observe(viewLifecycleOwner, Observer { viewEffect ->
            when (viewEffect) {
                is JokeDetailViewEffect.OpenJokeOnBrowser -> {
                    openJokeOnBrowser(viewEffect.jokeUrl)
                }
                JokeDetailViewEffect.UpdateFavoriteIcon -> {
                    binding.toolbarJokeDetail.menu.findItem(R.id.favoriteJokeItem)
                        .setIcon(R.drawable.ic_favorite)
                }
            }.exhaustive
        })
    }

    //region Views
    /**
     * Setup View Components
     */
    private fun setupViews() {
        setupToolbar()
    }

    /**
     * Setup Toolbar
     */
    private fun setupToolbar() {
        binding.toolbarJokeDetail.setupWithNavController(navController)

        arguments.category?.let { binding.toolbarJokeDetail.title = it.name }

        binding.toolbarJokeDetail.menu.findItem(R.id.favoriteJokeItem)
            .setOnMenuItemClickListener { menuItem ->
                viewModel.processEvent(
                    JokeDetailViewEvent.FavoriteJoke(currentJoke)
                )
                true
            }
    }
    //endregion

    /**
     * Open joke on the internet
     */
    private fun openJokeOnBrowser(jokeUrl: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(jokeUrl)).apply {
            startActivity(this)
        }
    }

    /**
     * Send event of loading to the ViewModel
     */
    private fun loadJokeFromInternet(category: CategoryUI) {
        viewModel.processEvent(JokeDetailViewEvent.LoadJokeFromInternet(category))
    }

    /**
     * Send event of loading to the ViewModel
     */
    private fun loadJokeFromArguments() {
        viewModel.processEvent(JokeDetailViewEvent.LoadJokeFromArguments(arguments.joke!!))
    }
}
