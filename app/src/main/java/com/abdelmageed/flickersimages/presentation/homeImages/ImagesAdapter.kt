package com.abdelmageed.flickersimages.presentation.homeImages

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.abdelmageed.flickersimages.databinding.ItemImagesBinding
import com.abdelmageed.flickersimages.extensions.applyImage
import com.abdelmageed.flickersimages.extensions.createImageUrl


class ImagesAdapter(
    private val images: MutableList<PhotoItem?> = mutableListOf(),
    val clickListener: (String) -> Unit
) : RecyclerView.Adapter<ImagesAdapter.JobsViewHolder>() {
    private lateinit var binding: ItemImagesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        binding = ItemImagesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JobsViewHolder(
            binding.root
        )
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        images[position]?.let { holder.bind(it) }
        holder.setIsRecyclable(false)
        if (position % 5 == 0 && position != 0) {
            binding.btnCard.visibility = View.VISIBLE
        } else {
            binding.btnCard.visibility = View.GONE
        }

    }

    fun addItems(imageList: MutableList<PhotoItem?>) {
        images.addAll(imageList)
        notifyItemInserted(images.size - 1)

    }

    fun getImages(): MutableList<PhotoItem?> {
        return images
    }

    override fun getItemCount() = images.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class JobsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: PhotoItem) {
            binding.apply {
                val imageUrl = "https://farm".createImageUrl(
                    image.farm.toString(),
                    image.server.toString(),
                    image.id.toString(),
                    image.secret.toString()
                )
                ivImage.applyImage(imageUrl)
                Log.e("image", image.title.toString())
                ivImage.setOnClickListener {
                    clickListener(imageUrl)
                }
            }
        }
    }
}