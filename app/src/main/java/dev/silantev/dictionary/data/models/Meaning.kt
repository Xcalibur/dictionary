package dev.silantev.dictionary.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meaning(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("soundUrl")
    val soundUrl: String?,
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("translation")
    val translation: Translation?
) : Serializable