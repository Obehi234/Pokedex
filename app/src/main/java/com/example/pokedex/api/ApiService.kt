package com.example.pokedex.api

import com.example.pokedex.pokemonDetails.detailsModel.DetailsBluePrint
import com.example.pokedex.pokemonDirectory.pokemonModel.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/")
    suspend fun getPokemon() : Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String) : Response<DetailsBluePrint>
}