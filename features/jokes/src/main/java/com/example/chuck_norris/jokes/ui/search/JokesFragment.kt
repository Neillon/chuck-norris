package com.example.chuck_norris.jokes.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chuck_norris.jokes.databinding.FragmentJokesBinding

class JokesFragment : Fragment() {

    private lateinit var binding: FragmentJokesBinding

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
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {

    }
}