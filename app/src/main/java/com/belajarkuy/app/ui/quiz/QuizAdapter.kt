package com.belajarkuy.app.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajarkuy.app.data.model.QuestionsItem
import com.belajarkuy.app.databinding.ItemQuizBinding

class QuizAdapter(private val questions: MutableList<QuestionsItem>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizViewHolder =
        QuizViewHolder(ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: QuizAdapter.QuizViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size

    inner class QuizViewHolder(private var binding: ItemQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: QuestionsItem) {
            with(binding) {
                data.options.map {
                    binding = ItemQuizBinding.inflate(LayoutInflater.from(itemView.context), rgAnswer, false)
                    val radioButton = RadioButton(rgAnswer.context)
                    radioButton.text = it
                    rgAnswer.addView(radioButton)
                }
                tvQuestion.text = data.questions
            }
        }
    }
}