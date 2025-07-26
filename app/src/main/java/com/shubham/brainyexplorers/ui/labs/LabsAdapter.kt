package com.shubham.brainyexplorers.ui.labs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.brainyexplorers.data.model.LabItem
import com.shubham.brainyexplorers.databinding.ItemLabBinding

/**
 * RecyclerView Adapter for displaying LabItem list.
 * @param onItemClick Callback for item click events.
 */
class LabsAdapter(
    private val onItemClick: (LabItem) -> Unit
) : RecyclerView.Adapter<LabsAdapter.LabViewHolder>() {

    private val items = mutableListOf<LabItem>()

    fun submitList(newItems: List<LabItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabViewHolder {
        val binding = ItemLabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    /**
     * ViewHolder for LabItem.
     */
    inner class LabViewHolder(private val binding: ItemLabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LabItem) {
            binding.labTitleTextView.text = item.title
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
} 