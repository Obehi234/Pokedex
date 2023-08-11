package com.example.pokedex.pokemonDetails.sharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.pokemonDetails.detailsModel.DetailsBluePrint
import com.example.pokedex.pokemonDetails.repository.DetailsRepository
import com.example.pokedex.pokemonDirectory.repository.Repository
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val repository: DetailsRepository = DetailsRepository

    private val _details = MutableLiveData<DetailsBluePrint>()
    val details: LiveData<DetailsBluePrint> = _details

    private val _pokemonName = MutableLiveData<String>()
    val pokemonName: LiveData<String> = _pokemonName

    fun setPokemonName(name: String) {
        _pokemonName.value = name
    }

    suspend fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            val response = repository.getPokemonDetails(name)
            if (response.isSuccessful){
                if(response.body() != null){
                    _details.value = response.body()
                }
            }
        }

    }


}