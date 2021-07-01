package com.example.fanficrepo.entity

import com.example.fanficrepo.entity.Chapter
import com.squareup.moshi.Json

data class Fanfic(
    val id:  Int,
    val title: String,
    @Json(name = "username") val author: String,
    val description: String,
    val fandom: String,
    val chapters: List<Chapter>?,
    val tags: List<String>?
)