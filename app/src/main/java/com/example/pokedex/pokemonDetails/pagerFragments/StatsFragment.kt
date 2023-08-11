package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokedex.databinding.FragmentStatsBinding
import com.example.pokedex.pokemonDetails.sharedViewModel.SharedViewModel


class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private var pokemonName: String? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var statsAdapter: StatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        sharedViewModel.pokemonName.observe(viewLifecycleOwner) { name ->
            pokemonName = name
        }
        sharedViewModel.details.observe(viewLifecycleOwner) { details ->
            binding.statsTV.text = details.stats.size.toString()
            val statsList = details.stats

            statsAdapter = StatsAdapter(statsList)
            binding.statsListView.apply {
                adapter = statsAdapter
                setHasFixedSize(true)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}