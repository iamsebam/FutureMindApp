package com.sebastianmatyjaszczyk.listfeature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sebastianmatyjaszczyk.listfeature.databinding.ListViewItemBinding
import com.sebastianmatyjaszczyk.listfeature.domain.ListViewItem

class ListViewAdapter(
    private val onItemSelectedListener: (item: ListViewItem) -> Unit
) : ListAdapter<ListViewItem, ListViewAdapter.ListViewHolder>(ListViewItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, onItemSelectedListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ListViewHolder(
        private val binding: ListViewItemBinding,
        private val onItemSelectedListener: (ListViewItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewItem: ListViewItem) {
            with(binding) {
                title.text = viewItem.title
                description.text = viewItem.description
                modificationDate.text = viewItem.modificationDate
                // TODO show image - lazy load, handle progress bar and errors
                root.setOnClickListener { onItemSelectedListener }
            }
        }
    }

    private class ListViewItemDiffCallback : DiffUtil.ItemCallback<ListViewItem>() {

        override fun areItemsTheSame(oldItem: ListViewItem, newItem: ListViewItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListViewItem, newItem: ListViewItem) =
            oldItem == newItem
    }
}
