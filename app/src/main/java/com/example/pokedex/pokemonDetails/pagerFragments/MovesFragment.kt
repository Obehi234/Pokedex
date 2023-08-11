package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokedex.databinding.FragmentMovesBinding
import com.example.pokedex.pokemonDetails.sharedViewModel.SharedViewModel

class MovesFragment : Fragment() {
    private var _binding: FragmentMovesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movesAdapter: MovesAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var pokemonName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovesFragment()
    }

    private fun setUpMovesFragment() {
        sharedViewModel.pokemonName.observe(viewLifecycleOwner) { name ->
            pokemonName = name
        }
        sharedViewModel.details.observe(viewLifecycleOwner) { details ->
            binding.movesTV.text = details.moves.size.toString()
            val movesList = details.moves

            movesAdapter = MovesAdapter(movesList)
            binding.movesRV.apply {
                adapter = movesAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
