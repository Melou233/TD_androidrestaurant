package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews.RemoteCollectionItems
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CellCustomBinding
import com.example.myapplication.network.Plate


class CustomAdapter(val items: kotlin.collections.List<Plate>, val clickListener:(Plate)->(Unit)):RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {
    class CellViewHolder(binding: CellCustomBinding) : RecyclerView.ViewHolder(binding.root){
        val textView: TextView =binding.itemName
        val root = binding.root
        val priceTextView=binding.priceTextView
        val imageView=binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CellViewHolder{
        val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val plate = items[position]
        holder.textView.text= plate.name
        holder.priceTextView.text = plate.prices.first().price + " €"
        holder.root.setOnClickListener{
            Log.d("Click","click sur cell $position")
            clickListener(plate)
        }
    }
}