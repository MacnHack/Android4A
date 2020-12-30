package com.example.android4a.presentation.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.Interface.IItemClickListener
import com.example.android4a.R
import com.example.android4a.data.local.Common.Common
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.chip_item.view.*

class PokemonTypeAdapter(internal var context: Context, internal var typeList:List<String>): RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = typeList[position]
        holder.chip.chipBackgroundColor = ColorStateList.valueOf(Common.getColorByType(typeList[position]))
    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var chip: Chip

        init {
            chip = itemView.findViewById(R.id.chip) as Chip
            chip.setOnClickListener { Toast.makeText(context, "Oui, c'est bien le type " + chip.text, Toast.LENGTH_SHORT).show() }
        }
    }
}