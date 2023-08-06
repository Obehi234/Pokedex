package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FragmentStatsBinding
import com.example.pokedex.pokemonDetails.viewmodel.DetailsViewModel


private const val ARG_PARAM1 = "pokemonName"

class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private var pokemonName: String? = null
    private lateinit var statsTV: TextView
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var statsListView: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonName = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        statsTV = binding.statsTV
        statsListView = binding.statsListView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        pokemonName?.let { detailsViewModel.showPokemonDetails(it) }
        detailsViewModel.details.observe(viewLifecycleOwner, Observer { details ->
            statsTV.text = details.stats.size.toString()
            val statsList = details.stats

            statsAdapter = StatsAdapter(statsList)
            statsListView.adapter = statsAdapter
            statsListView.setHasFixedSize(true)

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonName: String) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, pokemonName)
                }
            }
    }
}