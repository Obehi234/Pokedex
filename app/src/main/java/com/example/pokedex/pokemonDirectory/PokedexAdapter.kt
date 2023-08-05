package com.example.pokedex.pokemonDirectory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.PokemonRowItemBinding
import com.example.pokedex.pokemonDirectory.pokemonModel.Result
import com.squareup.picasso.Picasso

class PokedexAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<Result, PokedexAdapter.PokemonViewHolder>(PokemonDiffUtilCallback()) {

    interface OnItemClickListener {
        fun onItemClick(pokemonNumber: Int, pokemonName: String)
    }

    inner class PokemonViewHolder(private val binding: PokemonRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setSerialNumber(number: Int) {
            val formattedNumber = String.format("#%03d", number)
            binding.pokemonNumber.text = formattedNumber
        }

        fun bind(item: Result) {
            binding.pokemonName.text = item.name
            val imageNo = item.url.split("https://pokeapi.co/api/v2/pokemon/")[1].split("/")[0]

            Picasso.with(binding.root.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$imageNo.png")
                .into(binding.pokemonImage)

            binding.root.setOnClickListener {
                val pokemonNumber = binding.pokemonNumber.text.toString().substring(1).toInt()
                val pokemonName = item.name
                itemClickListener.onItemClick(pokemonNumber, pokemonName)
            }
        }
    }

    class PokemonDiffUtilCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonRowItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        val serialNumber = position + 1

        holder.setSerialNumber(serialNumber)
        holder.bind(item)
    }

}