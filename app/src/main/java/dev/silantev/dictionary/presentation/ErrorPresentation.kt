package dev.silantev.dictionary.presentation

import android.content.Context
import android.widget.TextView
import dev.silantev.dictionary.R
import java.lang.Exception

.*

/**
 * Отображает сообщение об ошибке на TextView
 */
class ErrorPresentation(private val context: Context) : Presentation<Exception, TextView> {
    private fun getText(e: Exception) : String {
        return e.localizedMessage ?: context.getString(R.string.error_unknown)
    }

    override fun present(data: Exception, view: TextView) {
        // Для примера берется просто сообщение из самого Exception
        // В реальном коде чаще всего нужно разобрать что же за ошибка произошла и отобразить
        // понятное пользователю и локализованное сообщения (для этого здесь нужен контекст)
        view.text = getText(data)
    }
}