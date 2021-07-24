package net.s1mple.ulanganlaravel.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import net.s1mple.ulanganlaravel.R

object GlideHelper {

    fun setImage(context: Context, urlImage: String, imageView: ImageView) {
        Glide.with(context)
            .load(urlImage)
            .centerCrop()
            .placeholder(R.drawable.img_no_image)
            .error(R.drawable.img_no_image)
            .into(imageView)
    }

}