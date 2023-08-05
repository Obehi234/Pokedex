package com.example.pokedex.pokemonDetails.pagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex.pokemonDetails.pagerFragments.MovesFragment
import com.example.pokedex.pokemonDetails.pagerFragments.StatsFragment


class PokemonDetailsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovesFragment()
            1 -> StatsFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}
