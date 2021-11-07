package dev.silantev.dictionary.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.silantev.dictionary.data.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = Repository()

    private val _state = MutableLiveData<SearchUIState>()
    val stateSearch: LiveData<SearchUIState> = _state

    fun search(query: String) {
        _state.postValue(SearchUIState.Loading)
        viewModelScope.launch {
            try {
                repository.queryWords(query)
                    .collect {
                        _state.postValue(SearchUIState.Success(it))
                    }
            } catch (e: Exception) {
                _state.postValue(SearchUIState.Error(e))
            }
        }
    }
}