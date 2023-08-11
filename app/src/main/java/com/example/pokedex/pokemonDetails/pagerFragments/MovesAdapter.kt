package com.example.pokedex.pokemonDetails.pagerFragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FormsRecyclerItemBinding
import com.example.pokedex.pokemonDetails.detailsModel.Move

class MovesAdapter(private val movesList: List<Move>) :
    RecyclerView.Adapter<MovesAdapter.MovesViewHolder>() {

    inner class MovesViewHolder(private val binding: FormsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(move: Move) {
            binding.abilityName.text = move.move.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FormsRecyclerItemBinding.inflate(inflater, parent, false)
        return MovesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        holder.bind(movesList[position])
    }

    override fun getItemCount(): Int = movesList.size

}

