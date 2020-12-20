package com.example.chuck_norris.jokes.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.databinding.FragmentJokeDetailBinding

class JokeDetailFragment : Fragment() {

    private lateinit var binding: FragmentJokeDetailBinding
    private val arguments: JokeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

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

    }
}