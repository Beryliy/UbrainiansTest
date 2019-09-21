package com.flogiston.test.presentation.extract.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flogiston.test.R
import kotlinx.android.synthetic.main.image_table_item.view.*
import java.io.File

class ImageTableAdapter : RecyclerView.Adapter<ImageTableAdapter.ImageViewHolder>() {
    var fileList = arrayOf<File>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_table_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.itemImage).load(fileList[position]).placeholder(R.drawable.image_placeholder).into(holder.itemImage)
    }


    override fun getItemCount() = fileList.size

    inner class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.itemImage
    }
}