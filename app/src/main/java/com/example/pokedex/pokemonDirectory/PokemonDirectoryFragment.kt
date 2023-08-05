package com.example.pokedex.pokemonDirectory

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FragmentPokemonDirectoryBinding
import com.example.pokedex.pokemonDirectory.viewmodel.PokemonViewModel


class PokemonDirectoryFragment : Fragment(), PokedexAdapter.OnItemClickListener {
    private var _binding: FragmentPokemonDirectoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var rvDirectory: RecyclerView
    private lateinit var pgBar: ProgressBar
    private lateinit var tvInternet: TextView
    private lateinit var ivInternet: ImageView
    private lateinit var rvAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDirectoryBinding.inflate(inflater, container, false)
        ivInternet = binding.ivInternet
        tvInternet = binding.tvInternet
        rvDirectory = binding.rvPokedex
        pgBar = binding.progressBar
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
            ivInternet.visibility = View.GONE
            tvInternet.visibility = View.GONE
            setUpRV()
        } else {
            ivInternet.visibility = View.VISIBLE
            tvInternet.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(pokemonNumber: Int, pokemonName: String) {
        val action =
            PokemonDirectoryFragmentDirections.navigateToDetailsFragment(pokemonName, pokemonNumber)
        findNavController().navigate(action)
        Log.d("CHECK_ARGS", "arguments - $pokemonNumber - $pokemonName")
    }

    private fun setUpRV() {
        rvDirectory.setHasFixedSize(true)
        rvDirectory.adapter = rvAdapter
        pokemonViewModel.populatePokemonDirectoryFromApi()
        showProgressBar()
        pokemonViewModel.pokemon.observe(viewLifecycleOwner, Observer {
            hideProgressBar()
            rvAdapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        pgBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pgBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}