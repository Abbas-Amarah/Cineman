package com.example.cinemana.movie_detail.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logo_path")
    val logoPath: Any? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)