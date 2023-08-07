package com.example.pokedex.pokemonDetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.pokemonDetails.detailsModel.DetailsBluePrint
import com.example.pokedex.pokemonDetails.repository.DetailsRepository
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository: DetailsRepository = DetailsRepository
    private var _details = MutableLiveData<DetailsBluePrint>()
    val details: LiveData<DetailsBluePrint>
    get() = _details

    fun showPokemonDetails(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetails(name)
                if(response.isSuccessful) {
                    if (response.body() != null) {
                        _details.value = response.body()
                    }
                }
            } catch (_: Exception) {

            }
        }
    }

}