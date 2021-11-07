package dev.silantev.dictionary

import dev.silantev.dictionary.data.Api
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiTest {
    @Test
    fun search() {
        val api = Api.create()

        runBlocking {
           val response = api.searchWord("go")
            assertNotEquals(0, response.size)
        }

        assertTrue(true)
    }
}