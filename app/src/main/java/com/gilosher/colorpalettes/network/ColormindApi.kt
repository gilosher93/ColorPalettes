package com.gilosher.colorpalettes.network

import retrofit2.http.Body
import retrofit2.http.POST

interface ColormindApi {

    @POST("api/")
    suspend fun getRandomColors(
        @Body body: RandomColorRequestBody
    ): RandomColorResponse

}