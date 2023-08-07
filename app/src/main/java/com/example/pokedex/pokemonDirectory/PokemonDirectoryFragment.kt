package com.example.pokedex.pokemonDirectory

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokedex.databinding.FragmentPokemonDirectoryBinding
import com.example.pokedex.pokemonDirectory.viewmodel.PokemonViewModel


class PokemonDirectoryFragment : Fragment(), PokedexAdapter.OnItemClickListener {
    private var _binding: FragmentPokemonDirectoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var rvAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDirectoryBinding.inflate(inflater, container, false)
        rvAdapter = PokedexAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        checkInternet()
    }

    private fun checkInternet() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val isConnected =
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

        if (isConnected) {
            binding.ivInternet.visibility = View.GONE
            binding.tvInternet.visibility = View.GONE
            setUpRV()
        } else {
            binding.ivInternet.visibility = View.VISIBLE
            binding.tvInternet.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(pokemonNumber: Int, pokemonName: String) {
        val action =
            PokemonDirectoryFragmentDirections.navigateToDetailsFragment(pokemonName, pokemonNumber)
        findNavController().navigate(action)
    }

    private fun setUpRV() {
        binding.rvPokedex.apply {
            setHasFixedSize(true)
            adapter = rvAdapter
        }
        pokemonViewModel.populatePokemonDirectoryFromApi()
        showProgressBar()
        pokemonViewModel.pokemon.observe(viewLifecycleOwner, Observer {
            hideProgressBar()
            rvAdapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}