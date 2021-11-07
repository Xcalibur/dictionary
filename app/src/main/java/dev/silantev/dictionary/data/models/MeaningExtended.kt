package dev.silantev.dictionary.data.models
import com.google.gson.annotations.SerializedName

data class MeaningExtended(
    @SerializedName("alternativeTranslations")
    val alternativeTranslations: List<AlternativeTranslation>?,
    @SerializedName("definition")
    val definition: Definition?,
    @SerializedName("difficultyLevel")
    val difficultyLevel: Int?,
    @SerializedName("examples")
    val examples: List<Example>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("meaningsWithSimilarTranslation")
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>?,
    @SerializedName("mnemonics")
    val mnemonics: String?,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String?,
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("properties")
    val properties: Properties?,
    @SerializedName("soundUrl")
    val soundUrl: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("translation")
    val translation: TranslationXX?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("wordId")
    val wordId: Int?
)

data class AlternativeTranslation(
    @SerializedName("text")
    val text: String?,
    @SerializedName("translation")
    val translation: Translation?
)

data class Definition(
    @SerializedName("soundUrl")
    val soundUrl: String?,
    @SerializedName("text")
    val text: String?
)

data class Example(
    @SerializedName("soundUrl")
    val soundUrl: String?,
    @SerializedName("text")
    val text: String?
)

data class Image(
    @SerializedName("url")
    val url: String?
)

data class MeaningsWithSimilarTranslation(
    @SerializedName("frequencyPercent")
    val frequencyPercent: String?,
    @SerializedName("meaningId")
    val meaningId: Int?,
    @SerializedName("partOfSpeechAbbreviation")
    val partOfSpeechAbbreviation: String?,
    @SerializedName("translation")
    val translation: Translation?
)

class Properties

data class TranslationXX(
    @SerializedName("note")
    val note: String?,
    @SerializedName("text")
    val text: String?
)
