package com.sebastianmatyjaszczyk.mainfeature.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sebastianmatyjaszczyk.mainfeature.R
import com.sebastianmatyjaszczyk.mainfeature.databinding.ListViewItemBinding
import com.sebastianmatyjaszczyk.mainfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.mainfeature.presentation.misc.ImageViewConstants
import com.sebastianmatyjaszczyk.networklib.MOCK_IMAGE_SERVICE_URL

class ListViewAdapter(
    private val onItemSelectedListener: (item: ListItemViewEntity) -> Unit
) : ListAdapter<ListItemViewEntity, ListViewAdapter.ListViewHolder>(ListViewItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, onItemSelectedListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ListViewHolder(
        private val binding: ListViewItemBinding,
        private val onItemSelectedListener: (ListItemViewEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewItem: ListItemViewEntity) {
            with(binding) {
                title.text = viewItem.title
                description.text = viewItem.description
                modificationDate.text = viewItem.modificationDate
                // unfortunately the provided image mocking service doesn't work, I took the liberty of replacing it with bears photos :)
                image.load(MOCK_IMAGE_SERVICE_URL) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    transformations(RoundedCornersTransformation(ImageViewConstants.cornerRadius))
                }
                root.setOnClickListener { onItemSelectedListener(viewItem) }
            }
        }
    }

    private class ListViewItemDiffCallback : DiffUtil.ItemCallback<ListItemViewEntity>() {

        override fun areItemsTheSame(oldItem: ListItemViewEntity, newItem: ListItemViewEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListItemViewEntity, newItem: ListItemViewEntity) =
            oldItem == newItem
    }
}
