package dev.silantev.dictionary.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.silantev.dictionary.R
import dev.silantev.dictionary.data.models.Meaning
import dev.silantev.dictionary.databinding.LayoutWordMeaningBinding
import dev.silantev.dictionary.presentation.ImagePresentation

class MeaningsAdapter : ListAdapter<Meaning, MeaningsAdapter.ViewHolder>(Diff()) {
    private val imagePresentation = ImagePresentation()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutWordMeaningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, imagePresentation)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: LayoutWordMeaningBinding, private val imagePresentation: ImagePresentation) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Meaning) {
            with(binding) {
                partOfSpeech.text = item.partOfSpeechCode
                transcription.text = transcription.resources.getString(R.string.transcription_template, item.transcription)
                translation.text = item.translation?.text
                note.text = item.translation?.note
                imagePresentation.present(item.imageUrl, image)
            }
        }

    }

    private class Diff : DiffUtil.ItemCallback<Meaning>() {
        override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem == newItem
        }
    }
}