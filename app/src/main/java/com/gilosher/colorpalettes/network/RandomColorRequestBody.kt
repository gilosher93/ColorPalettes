package com.gilosher.colorpalettes.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RandomColorRequestBody(
    @SerializedName("model")
    val model: String = "default"
)