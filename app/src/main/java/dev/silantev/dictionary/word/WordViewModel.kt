package dev.silantev.dictionary.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.silantev.dictionary.data.Repository
import dev.silantev.dictionary.data.models.Meaning
import dev.silantev.dictionary.data.models.MeaningExtended
import dev.silantev.dictionary.data.models.Word
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WordViewModel : ViewModel() {
    private val _meanings = MutableLiveData<List<Meaning>>()
    private val _text = MutableLiveData<String>()

    var word: Word? = null
        set(value) {
            field = value
            _meanings.postValue(value?.meanings ?: listOf())
            _text.postValue(word?.text ?: "")
        }

    val meanings: LiveData<List<Meaning>> = _meanings
    val text : LiveData<String> = _text
}