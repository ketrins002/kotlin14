package com.example.kotlin13

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin13.databinding.ItemBinding
import kotlin.random.Random

class MyAdapter(private val items: List<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var styleChanged = false

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String, changeStyle: Boolean) {
            binding.itemTextView.text = text
            if (changeStyle) {
                binding.itemTextView.setTextColor(Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                binding.itemTextView.textSize = Random.nextInt(14, 24).toFloat()
            } else {
                binding.itemTextView.setTextColor(Color.BLACK)
                binding.itemTextView.textSize = 18f
            }
        }
    }

    fun changeStyle() {
        styleChanged = !styleChanged
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], styleChanged)
    }

    override fun getItemCount() = items.size
}
