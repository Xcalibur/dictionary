package dev.silantev.dictionary.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Translation(
    @SerializedName("note")
    val note: String?,
    @SerializedName("text")
    val text: String?
) : Serializable