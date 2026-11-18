package com.example.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.example.pertemuan10.databinding.ItemRecyclerBinding

class AdapterRecView(private val listWayang: ArrayList<DcWayang>) :
    RecyclerView.Adapter<AdapterRecView.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: DcWayang)
        fun delData(pos:Int)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

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

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        holder.binding.gambarWayang.load(wayang.foto) {
            crossfade(true)
            placeholder(circularProgressDrawable)
            error(R.drawable.ic_launcher_foreground)
        }

        holder.binding.gambarWayang.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Gambar ${wayang.nama} diklik",
                Toast.LENGTH_SHORT
            ).show()

            if (::onItemClickCallback.isInitialized){
                onItemClickCallback.onItemClicked(wayang)
            }

        }

        holder.itemView.setOnClickListener {
            if (::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClicked(wayang)
            }
        }
        holder.binding.btnHapus.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION && ::onItemClickCallback.isInitialized) {
                onItemClickCallback.delData(currentPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    class ListViewHolder(val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)
}