package com.belajarkuy.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belajarkuy.app.data.model.CompetencyItem
import com.belajarkuy.app.databinding.ItemRecommendationBinding

class RecommendationAdapter(
    private val modules: MutableList<CompetencyItem>,
    private val listener: Listener
) : RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder =
        RecommendationViewHolder(ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(modules[position], listener)
    }

    override fun getItemCount(): Int = modules.size

    inner class RecommendationViewHolder(private val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CompetencyItem, listener: Listener) {
            val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_list_item_1, android.R.id.text1, data.material)
            with(binding) {
                tvSubject.text = data.subject
                lvMaterial.adapter = adapter

                btnTrain.setOnClickListener {
                    listener.onClick(data)
                }
            }
        }
    }

    interface Listener {
        fun onClick(modules: CompetencyItem)
    }
}