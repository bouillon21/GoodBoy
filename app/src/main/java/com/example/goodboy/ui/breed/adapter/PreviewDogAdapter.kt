package com.example.goodboy.ui.breed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.goodboy.R
import com.example.goodboy.data.model.Dog
import com.example.goodboy.ui.breed.viewholder.PreviewDogViewHolder
import com.example.goodboy.ui.diffutil.DiffUtilCallbackImp

class PreviewDogAdapter(private val onClick: (name: String) -> Unit) :
    RecyclerView.Adapter<PreviewDogViewHolder>() {

    var dogs = ArrayList<Dog>()
        set(value) {
            val callback = DiffUtilCallbackImp(
                oldItems = field,
                newItems = value,
                { oldItem: Dog, newItem -> oldItem === newItem },
                { oldItem: Dog, newItem -> oldItem.breed == newItem.breed }
            )
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewDogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PreviewDogViewHolder(
            onClick,
            layoutInflater.inflate(R.layout.dog_item_preview, parent, false)
        )
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: PreviewDogViewHolder, position: Int) {
        holder.onBind(dogs[position])
    }

}