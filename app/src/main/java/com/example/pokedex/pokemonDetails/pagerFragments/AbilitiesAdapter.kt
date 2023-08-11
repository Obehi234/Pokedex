package com.example.pokedex.pokemonDetails.pagerFragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FormsRecyclerItemBinding
import com.example.pokedex.pokemonDetails.detailsModel.Ability

class AbilitiesAdapter(private val abilitiesList: List<Ability>):
RecyclerView.Adapter<AbilitiesAdapter.AbilityViewHolder>(){

    inner class AbilityViewHolder(private val binding: FormsRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(ability: Ability) {
            binding.abilityName.text = ability.ability.name
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FormsRecyclerItemBinding.inflate(inflater, parent, false)
        return AbilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.bind(abilitiesList[position])
    }

    override fun getItemCount(): Int = abilitiesList.size
}