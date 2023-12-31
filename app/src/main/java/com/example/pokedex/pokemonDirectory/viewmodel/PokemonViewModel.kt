package com.example.pokedex.pokemonDirectory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.pokemonDirectory.pokemonModel.Result
import com.example.pokedex.pokemonDirectory.repository.Repository
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val repository: Repository = Repository
    private var _pokemon = MutableLiveData<List<Result>>()
    val pokemon: LiveData<List<Result>>
        get() = _pokemon

    fun populatePokemonDirectoryFromApi() {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonResult()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val pokemonList = response.body()
                        _pokemon.value = pokemonList?.results ?: listOf()
                    }
                }
            } catch (_: Exception) {
            }
        }
    }
}