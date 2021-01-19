package com.englishgalaxy.traveltestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishgalaxy.traveltestapp.databinding.ItemMapBinding
import com.englishgalaxy.traveltestapp.net.responce.ItemPlaces
import com.englishgalaxy.traveltestapp.net.responce.Place

class MapAdapter : RecyclerView.Adapter<MapAdapter.ViewHolder>() {

    class ViewHolder(val mapItem: ItemMapBinding) : RecyclerView.ViewHolder(mapItem.root) {
    }

    private var listPlaces = listOf<Place>()
    private var clickListenerCallback: ((lat: Double, long: Double) -> Unit)? = null

    fun clickListener(po: (id: Double, long: Double) -> Unit) {
        clickListenerCallback = po
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPlaces[position]
        with(holder) {
            with(mapItem) {
                tvNamePlace.text = item.name
                mapItem.rootView.setOnClickListener {
                    clickListenerCallback?.invoke(item.lat, item.lng)
                }
            }
        }
    }

    override fun getItemCount(): Int = listPlaces.size

    fun setList(item: ItemPlaces) {
        listPlaces = item.places
        notifyDataSetChanged()
    }
}