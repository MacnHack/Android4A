package com.example.android4a.presentation.Adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.data.local.Common.Common
import com.example.android4a.domain.entity.Evolution
import com.google.android.material.chip.Chip

class PokemonEvolutionAdapter(internal var context: Context, var evolutionList:List<Evolution>?): RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return evolutionList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = evolutionList!![position].name
        holder.chip.chipBackgroundColor = ColorStateList.valueOf(Common.getColorByType(Common.findPokemonByNum(evolutionList!![position].num!!)!!.type!![0]))
    }

    init {
        if (evolutionList == null)
            evolutionList = ArrayList()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var chip: Chip

        init {
            chip = itemView.findViewById(R.id.chip) as Chip
            chip.setOnClickListener {
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_NUM_EVOLUTION).putExtra("num", evolutionList!![adapterPosition].num))
            }
        }
    }
}