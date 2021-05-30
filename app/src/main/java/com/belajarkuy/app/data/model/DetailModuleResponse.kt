package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModuleResponse(

	@field:SerializedName("questions")
	val questions: List<QuestionsItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class QuestionsItem(

	@field:SerializedName("questions")
	val questions: String,

	@field:SerializedName("options")
	val options: List<String>,

	@field:SerializedName("correct_answer")
	val correctAnswer: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
