package com.example.goodboy.ui.breed.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.goodboy.R
import com.example.goodboy.data.model.Dog

class PreviewDogViewHolder(private val onClick: (name: String) -> Unit, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val imageDog: ImageView = itemView.findViewById(R.id.dog_image)

    private val breedDog: TextView = itemView.findViewById(R.id.dog_breed)

    private val pbDogImage: ProgressBar = itemView.findViewById(R.id.dog_progress_bar_item_preview)

    private val cvDogBreed: CardView = itemView.findViewById(R.id.dog_card_view)

    fun onBind(dog: Dog) {
        breedDog.text = dog.breed
        imageDog.load(dog.image?.url ?: R.drawable.image_plug) {
            crossfade(true)
            listener(onSuccess = { _, _ ->
                pbDogImage.isVisible = false
            })
        }
        cvDogBreed.setOnClickListener {
            dog.breed?.let { onClick.invoke(it) }
        }
    }

}