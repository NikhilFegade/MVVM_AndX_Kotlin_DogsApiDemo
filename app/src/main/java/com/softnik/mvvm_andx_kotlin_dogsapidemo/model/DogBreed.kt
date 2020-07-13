package com.softnik.mvvm_andx_kotlin_dogsapidemo.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("id")
    val breedId: String?,

    @SerializedName("names")
    val dogBreed: String?,

    @SerializedName("life_span")
    val lifeSpan: String?,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("bred_for")
    val breedFor: String?,

    @SerializedName("temperament")
    val temperament: String?,

    @SerializedName("url")
    val imageUrl: String?

)