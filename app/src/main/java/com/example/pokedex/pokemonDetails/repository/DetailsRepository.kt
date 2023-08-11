package com.example.pokedex.pokemonDetails.repository

import com.example.pokedex.api.ApiService
import com.example.pokedex.api.RetrofitInstance
import com.example.pokedex.pokemonDetails.detailsModel.DetailsBluePrint
import retrofit2.Response

object DetailsRepository {

    private val apiService: ApiService =
        RetrofitInstance.getInstance().create(ApiService::class.java)

    suspend fun getPokemonDetails(name: String) : Response<DetailsBluePrint>{
        return apiService.getPokemonDetails(name)
    }
}