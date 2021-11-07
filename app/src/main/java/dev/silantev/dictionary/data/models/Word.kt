package dev.silantev.dictionary.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Word(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("meanings")
    val meanings: List<Meaning>?,
    @SerializedName("text")
    val text: String?
) : Serializable