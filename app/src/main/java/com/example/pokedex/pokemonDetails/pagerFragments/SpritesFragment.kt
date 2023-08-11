package com.example.pokedex.pokemonDetails.pagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokedex.databinding.FragmentSpritesBinding
import com.example.pokedex.pokemonDetails.sharedViewModel.SharedViewModel
import com.squareup.picasso.Picasso


class SpritesFragment : Fragment() {

    private var _binding: FragmentSpritesBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var pokemonName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.pokemonName.observe(viewLifecycleOwner) { name ->
            pokemonName = name
        }
        sharedViewModel.details.observe(viewLifecycleOwner){details ->
            Picasso.with(binding.root.context)
                .load(details.sprites.front_shiny)
                .into(binding.sprite1)

            Picasso.with(binding.root.context)
                .load(details.sprites.front_default)
                .into(binding.sprite2)

            Picasso.with(binding.root.context)
                .load(details.sprites.back_shiny)
                .into(binding.sprite3)

            Picasso.with(binding.root.context)
                .load(details.sprites.back_default)
                .into(binding.sprite4)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}