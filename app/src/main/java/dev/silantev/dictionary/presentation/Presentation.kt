package dev.silantev.dictionary.presentation

import android.view.View

/**
 * База для отображения данных на UI.
 */
interface Presentation<TData, TView : View> {
    /**
     * Получает биндит данные на вью
     * @param data данные для отображения
     * @param view вью на котором данные будут отрисованы
     */
    fun present(data: TData, view: TView)
}