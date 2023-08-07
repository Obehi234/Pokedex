package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.FragmentMovesBinding
import com.example.pokedex.pokemonDetails.viewmodel.DetailsViewModel

private const val ARG_PARAM1 = "pokemonName"

class MovesFragment : Fragment() {
    private var _binding: FragmentMovesBinding? = null
    private val binding get() = _binding!!
    private var pokemonName: String? = null
    private lateinit var movesAdapter: MovesAdapter
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
        _binding = FragmentMovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovesFragment()
    }

    private fun setUpMovesFragment() {
        val detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        pokemonName?.let { detailsViewModel.showPokemonDetails(it) }
        detailsViewModel.details.observe(viewLifecycleOwner, Observer { details ->
            binding.movesTV.text = details.moves.size.toString()
            val moveList = details.moves

            movesAdapter = MovesAdapter(moveList)
            binding.movesRV.apply{
                adapter = movesAdapter
                setHasFixedSize(true)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonName: String) =
            MovesFragment().apply {
                arguments = Bundle().apply {
                    putString("pokemonName", pokemonName)

                }
            }
    }
}