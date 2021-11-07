package dev.silantev.dictionary

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.silantev.dictionary.data.Api
import dev.silantev.dictionary.data.Repository
import dev.silantev.dictionary.data.models.MeaningExtended
import dev.silantev.dictionary.data.models.Word
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dev.silantev.dictionary", appContext.packageName)
    }

    @Test
    fun searchTest() {
        val rep = Repository(api = mock())

        runBlocking {
            val found = rep.queryWords("go").last()

            assertEquals(1, found.size)
        }

    }

    fun mock() : Api {
        return object : Api {
            override suspend fun searchWord(search: String): List<Word> {
                return listOf(Word(0, listOf(), "go") )
            }

            override suspend fun getMeanings(ids: List<Int?>): List<MeaningExtended> {
                return emptyList()
            }
        }
    }
}