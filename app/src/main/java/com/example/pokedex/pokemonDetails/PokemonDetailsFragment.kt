package com.example.pokedex.pokemonDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex.pokemonDetails.pagerAdapter.PokemonDetailsPagerAdapter
import com.example.pokedex.pokemonDetails.pagerFragments.MovesFragment
import com.example.pokedex.pokemonDetails.pagerFragments.StatsFragment
import com.example.pokedex.pokemonDetails.viewmodel.DetailsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class PokemonDetailsFragment : Fragment() {
    private val args: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailsViewModel: DetailsViewModel
    private var pokemonName: String = ""
    private var pokemonNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            nameTV.text = pokemonName
            nameTV.setOnClickListener {
                findNavController().navigate(R.id.navigateToDirectoryFragment)
            }
            numberTV.text = String.format("#%03d", pokemonNumber)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        pokemonName = args.pokemonName
        pokemonNumber = args.pokemonNumber

        setUpImgFragment(pokemonName)

        val movesFragment = MovesFragment.newInstance(pokemonName)
        val statsFragment = StatsFragment.newInstance(pokemonName)

        val viewAdapter = PokemonDetailsPagerAdapter(childFragmentManager, lifecycle)
        viewAdapter.addFragment(movesFragment)
        viewAdapter.addFragment(statsFragment)

        binding.cardViewPager.adapter = viewAdapter

        val tabTitles = arrayOf("Moves", "Stats")
        TabLayoutMediator(binding.tabLayout, binding.cardViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    private fun setUpImgFragment(pokemonName: String) {
        detailsViewModel.showPokemonDetails(pokemonName)
        detailsViewModel.details.observe(viewLifecycleOwner, Observer { pokemonDetails ->
            val pokemonImage = pokemonDetails.sprites.back_default
            Picasso.with(binding.root.context)
                .load(pokemonImage)
                .into(binding.ivDetails)
        })

    }


}