package com.beranju.mandirinewsapp.core.remote.response

import com.google.gson.annotations.SerializedName

data class HeadLineResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String? = null
)




