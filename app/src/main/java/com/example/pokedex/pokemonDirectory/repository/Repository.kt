package com.example.pokedex.pokemonDirectory.repository

import com.example.pokedex.api.ApiService
import com.example.pokedex.api.RetrofitInstance
import com.example.pokedex.pokemonDirectory.pokemonModel.PokemonList
import retrofit2.Response

object Repository {
    private val apiService: ApiService =
        RetrofitInstance.getInstance().create(ApiService::class.java)

    suspend fun getPokemonResult(): Response<PokemonList> {
        return apiService.getPokemonForPokedex()
    }

}