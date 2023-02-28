package com.beranju.mandirinewsapp.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.beranju.mandirinewsapp.domain.model.NewsModel
import com.google.gson.Gson

/**
 * this is custom navtype for argument in navigation component compose
 * as we know passed object parcelable is antipattern in compose
 * so we not recommended to pass it
 *
 * but i found a way to pass object between navigation screen by custom navtype
 *
 * NewsModelType extend from navtype
 *
 * reff => https://proandroiddev.com/jetpack-compose-navigation-with-custom-navtype-9b44dd8820e
 */
class NewsModelType : NavType<NewsModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NewsModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): NewsModel {
        return Gson().fromJson(value, NewsModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NewsModel) {
        bundle.putParcelable(key, value)
    }

    // ** encapsulate the serialization with toString() method (optional)
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }


}