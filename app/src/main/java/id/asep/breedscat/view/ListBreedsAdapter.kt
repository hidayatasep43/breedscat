package id.asep.breedscat.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.databinding.ItemListBreedsBinding

/*
* Created by Asep Hidayat on 1/1/2021, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class BreedsAdapter(private val mListener: BreedsItemClickListener): ListAdapter<Breeds, BreedsAdapter.ViewHolder>(BreedsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, mListener)
    }

    class ViewHolder private constructor(val binding: ItemListBreedsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Breeds, listener: BreedsItemClickListener) {
            binding.item = item
            binding.breedsItemClick = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBreedsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface BreedsItemClickListener {
        fun onBreedsItemClicked(breeds: Breeds)
    }

}

class BreedsDiffCallback : DiffUtil.ItemCallback<Breeds>() {

    override fun areItemsTheSame(oldItem: Breeds, newItem: Breeds): Boolean {
        return oldItem.id.equals(newItem.id)
    }

    override fun areContentsTheSame(oldItem: Breeds, newItem: Breeds): Boolean {
        return oldItem == newItem
    }

}