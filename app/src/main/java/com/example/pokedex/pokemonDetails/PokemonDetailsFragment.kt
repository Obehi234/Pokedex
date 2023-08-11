package com.example.pokedex.pokemonDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex.pokemonDetails.pagerAdapter.PokemonDetailsPagerAdapter
import com.example.pokedex.pokemonDetails.pagerFragments.AbilitiesFragment
import com.example.pokedex.pokemonDetails.pagerFragments.MovesFragment
import com.example.pokedex.pokemonDetails.pagerFragments.SpritesFragment
import com.example.pokedex.pokemonDetails.pagerFragments.StatsFragment
import com.example.pokedex.pokemonDetails.sharedViewModel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PokemonDetailsFragment : Fragment() {
    private val args: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!
    private var pokemonName: String = ""
    private var pokemonNumber: Int = 0
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonName = args.pokemonName
        pokemonNumber = args.pokemonNumber

        sharedViewModel.setPokemonName(pokemonName)
        sharedViewModel.pokemonName.observe(viewLifecycleOwner){pokemon ->
            binding.nameTV.text = pokemon
        }
        lifecycleScope.launch {
            setUpImgFragment(pokemonName)
        }

        val movesFragment = MovesFragment()
        val statsFragment = StatsFragment()
        val spritesFragment = SpritesFragment()
        val abilitiesFragment = AbilitiesFragment()

        val viewAdapter = PokemonDetailsPagerAdapter(childFragmentManager, lifecycle)
        viewAdapter.addFragment(spritesFragment)
        viewAdapter.addFragment(movesFragment)
        viewAdapter.addFragment(statsFragment)
        viewAdapter.addFragment(abilitiesFragment)


        binding.cardViewPager.adapter = viewAdapter

        val tabTitles = arrayOf("Forms", "Moves","Stats", "Abilities")
        TabLayoutMediator(binding.tabLayout, binding.cardViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    private suspend fun setUpImgFragment(pokemonName: String) {
        sharedViewModel.getPokemonDetails(pokemonName)
        sharedViewModel.details.observe(viewLifecycleOwner){ pokemonDetails ->
            val pokemonImage = pokemonDetails.sprites.back_default
            Picasso.with(binding.root.context)
                .load(pokemonImage)
                .into(binding.ivDetails)
        }

    }


}