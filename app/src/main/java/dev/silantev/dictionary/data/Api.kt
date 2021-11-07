package dev.silantev.dictionary.data

import dev.silantev.dictionary.BuildConfig
import dev.silantev.dictionary.data.models.MeaningExtended
import dev.silantev.dictionary.data.models.Word
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    companion object {
        fun create(): Api {
            return Retrofit.Builder()
                .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
                .client(OkHttpClient.Builder()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                        }
                    }
                    .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

    @GET("words/search")
    suspend fun searchWord(@Query("search") search: String): List<Word>

    @GET("meanings")
    suspend fun getMeanings(@Query("ids") ids: List<Int?>): List<MeaningExtended>
}


