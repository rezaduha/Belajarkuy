package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModuleRequest(

	@field:SerializedName("ModuleRequest")
	val moduleRequest: List<ModuleRequestItem>
) : Parcelable

@Parcelize
data class ModuleRequestItem(

	@field:SerializedName("correct")
	val correct: Boolean,

	@field:SerializedName("questions")
	val questions: String
) : Parcelable
