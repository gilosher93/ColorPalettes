package com.gilosher.colorpalettes.network

import com.google.gson.annotations.SerializedName

data class RandomColorResponse(
    @SerializedName("result")
    val result: List<List<Int>>
)