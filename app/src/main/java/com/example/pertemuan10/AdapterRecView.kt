package com.example.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan10.databinding.ItemRecyclerBinding

class AdapterRecView (private val listWayang: ArrayList<DcWayang>): RecyclerView.Adapter<AdapterRecView.ListViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val wayang = listWayang[position]
        holder.binding.namaWayang.text = wayang.nama
        holder.binding.karakterWayang.text = wayang.karakter
        holder.binding.deskripsiWayang.text = wayang.deskripsi
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    class ListViewHolder (val binding: ItemRecyclerBinding):
            RecyclerView.ViewHolder(binding.root)
}