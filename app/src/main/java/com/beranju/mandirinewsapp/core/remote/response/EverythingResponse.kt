package com.beranju.mandirinewsapp.core.remote.response

import com.google.gson.annotations.SerializedName

data class EverythingResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String? = null
)
