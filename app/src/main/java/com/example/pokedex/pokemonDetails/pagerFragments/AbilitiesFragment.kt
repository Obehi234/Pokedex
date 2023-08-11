package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokedex.databinding.FragmentAbilitiesBinding
import com.example.pokedex.pokemonDetails.sharedViewModel.SharedViewModel


class AbilitiesFragment : Fragment() {
    private var _binding: FragmentAbilitiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var abilitiesAdapter : AbilitiesAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var pokemonName: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbilitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovesFragment()
    }

    private fun setUpMovesFragment() {
        sharedViewModel.pokemonName.observe(viewLifecycleOwner){name ->
            pokemonName = name
        }

        sharedViewModel.details.observe(viewLifecycleOwner){details ->
            binding.abilitiesTV.text = details.abilities.size.toString()
            val abilitiesList = details.abilities

            abilitiesAdapter = AbilitiesAdapter(abilitiesList)
            binding.abilitiesRV.apply{
                adapter = abilitiesAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}