package com.example.chuck_norris.jokes.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.databinding.FragmentJokesBinding
import com.example.chuck_norris.jokes.di.JokesModule
import com.example.chuck_norris.jokes.ui.search.adapter.JokesAdapter
import com.example.chuck_norris.jokes.ui.search.adapter.JokesItemClick
import com.example.chuck_norris.jokes.ui.search.data.SearchJokesViewEvent
import com.example.chuck_norris.ui.JokeUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

class SearchJokesFragment : Fragment(), JokesItemClick {

    private lateinit var binding: FragmentJokesBinding
    private val navController by lazy { findNavController() }
    private val viewModel: SearchJokesViewModel by viewModel()

    private val jokesAdapter = JokesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.joke_menu, menu)

        val menuItem: MenuItem = menu.findItem(R.id.searchJokeItem)
        val searchView = menuItem.actionView as SearchView

        setupSearchView(searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Setup the searchview
     */
    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String): Boolean {
                viewModel.processEvent(SearchJokesViewEvent.SearchJokeByDescriptionEvent(query))
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.processEvent(SearchJokesViewEvent.SearchJokeByDescriptionEvent(query))
                return true
            }
        })
    }

    /**
     * Inject dependencies
     */
    private fun injectDependencies() {
        koinApplication {
            unloadKoinModules(JokesModule.dependencies)
            loadKoinModules(JokesModule.dependencies)
        }
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {
        setupRecyclerViewJokes()
        setupToolbar()
    }

    /**
     * Setup Toolbar
     */
    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarJoke)
        setHasOptionsMenu(true)
    }

    /**
     * Setup recyclerViewJokes
     */
    private fun setupRecyclerViewJokes() {
        binding.recyclerViewJokes.adapter = jokesAdapter
        binding.recyclerViewJokes.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Observe changes into the current ViewState
     */
    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            binding.bottomInformationViewJokes.isVisible = viewState.isLoading

            jokesAdapter.insertData(viewState.jokes)

            viewState.error?.let { message ->
                binding.bottomInformationViewJokes.makeError(message)
            }

        })
    }

    override fun onItemClick(joke: JokeUI) {
        val action =
            SearchJokesFragmentDirections.actionSearchJokesFragmentToJokeDetailFragment(joke = joke)
        navController.navigate(action)
    }
}