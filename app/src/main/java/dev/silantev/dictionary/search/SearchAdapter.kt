package dev.silantev.dictionary.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.silantev.dictionary.data.models.Word
import dev.silantev.dictionary.databinding.LayoutSearchItemBinding

class SearchAdapter(private val delegate: (Word) -> Unit) : ListAdapter<Word, SearchAdapter.ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: LayoutSearchItemBinding, delegate: (Word) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var value: Word? = null

        init {
            binding.root.setOnClickListener {
                val v = value ?: return@setOnClickListener
                delegate.invoke(v)
            }
        }

        fun bind(item: Word) {
            value = item

            with(binding) {
                text.text = item.text
                meanings.text = item.meanings?.take(3)?.joinToString { it.translation?.text ?: "" }
            }
        }
    }

    private class Diff : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

    }
}