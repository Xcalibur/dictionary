package dev.silantev.dictionary.search

import dev.silantev.dictionary.data.models.Word

sealed class SearchUIState {
    data class Success(val list: List<Word>) : SearchUIState()
    data class Error(val exception: Exception) : SearchUIState()
    object Loading : SearchUIState()
}