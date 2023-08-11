package com.example.pokedex.pokemonDetails.pagerFragments


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FormsRecyclerItemBinding
import com.example.pokedex.pokemonDetails.detailsModel.Stat

class StatsAdapter(private val statsList: List<Stat>) :
    RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    inner class StatsViewHolder(binding: FormsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val statsName: TextView = binding.abilityName

        fun bind(stat: Stat) {
            statsName.text = stat.stat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FormsRecyclerItemBinding.inflate(inflater, parent, false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(statsList[position])
    }

    override fun getItemCount(): Int = statsList.size
}
