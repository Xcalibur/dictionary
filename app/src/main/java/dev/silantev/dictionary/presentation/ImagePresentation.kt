package dev.silantev.dictionary.presentation

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import dev.silantev.dictionary.R

/**
 * Враппер над пикасо. На случай если захотим поменять библиотеку для отображения нужно
 * будет исправить только этот класс.
 */
class ImagePresentation : Presentation<String?, ImageView> {

    override fun present(data: String?, view: ImageView) {
        Picasso.get()
            .load(Uri.parse("https:$data"))
            .placeholder(R.drawable.ic_picture)
            .into(view)
    }

}