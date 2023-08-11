package com.example.pokedex.pokemonDetails.detailsModel

data class DetailsBluePrint(
    val abilities: List<Ability>,
    val forms: List<Form>,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,

)