package com.belajarkuy.app.ui.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajarkuy.app.R
import com.belajarkuy.app.data.model.ModulesItem
import com.belajarkuy.app.databinding.ItemModuleBinding

class ModuleAdapter(
    private val modules: MutableList<ModulesItem>,
    private val listener: Listener
): RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder =
        ModuleViewHolder(ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(modules[position], listener)
    }

    override fun getItemCount(): Int = modules.size

    inner class ModuleViewHolder(private val binding: ItemModuleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModulesItem, listener: Listener) {
            with(binding) {
                tvSubject.text = data.subject
                tvTotalQuestion.text = itemView.context.resources.getString(R.string.total_questions, data.totalQuestions.toString())
                tvDuration.text = itemView.context.resources.getString(R.string.duration, data.duration.toString())
            }
            itemView.setOnClickListener {
                listener.onClick(data)
            }
        }
    }

    interface Listener {
        fun onClick(modules: ModulesItem)
    }
}