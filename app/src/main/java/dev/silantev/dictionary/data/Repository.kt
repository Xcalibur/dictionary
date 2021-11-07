package dev.silantev.dictionary.data

import android.util.LruCache
import dev.silantev.dictionary.data.models.MeaningExtended
import dev.silantev.dictionary.data.models.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val api: Api = Api.create()) {

    private val cache = LruCache<String, List<Word>>(100)

    fun queryWords(word: String) = flow {
        var entry = cache[word]
        if (entry == null) {
            entry = api.searchWord(word)
            cache.put(word, entry)
        }

        emit(entry)
    }

    fun queryMeanings(word: Word) = flow {
        val ids = word.meanings?.map { it.id } ?: return@flow
        emit(api.getMeanings(ids))
    }

}