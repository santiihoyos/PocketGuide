package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.HeroResponse
import com.santiihoyos.api_marvel.response.HeroesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RetrofitMarvelRestRepository : MarvelRestRepository {

    companion object {

        private const val API_URL = "https://developer.marvel.com/api/"
        private const val HEADER_ACCEPT_JSON = "Accept: application/json"
        private const val HEADER_CONTENT_TYPE_JSON = "Content-Type: application/json"

        private lateinit var instance: RetrofitMarvelRestRepository

        /**
         * Build if it is necessary create RetrofitRestRepository or existing instance
         *
         * @return RetrofitRestRepository implementation instance
         */
        fun getInstance(): RetrofitMarvelRestRepository {

            if (!Companion::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitMarvelRestRepository::class.java)
            }

            return instance
        }
    }

    @GET("character")
    @Headers(HEADER_ACCEPT_JSON, HEADER_CONTENT_TYPE_JSON)
    override suspend fun getCharactersAtPage(@Query("page") page: Int): HeroesResponse

    @GET("character/{id}")
    @Headers(HEADER_ACCEPT_JSON, HEADER_CONTENT_TYPE_JSON)
    override suspend fun getCharacterById(@Path("id") id: String): HeroResponse?

    //Add more Rick&Morty universe endpoints.
}
