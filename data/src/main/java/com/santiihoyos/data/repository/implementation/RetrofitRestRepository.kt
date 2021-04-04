package com.santiihoyos.data.repository.implementation

import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.data.response.CharacterResponse
import com.santiihoyos.data.response.CharactersResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RetrofitRestRepository : RestRepository {

    companion object {

        private const val API_URL = "https://rickandmortyapi.com/api/"
        private const val HEADER_ACCEPT_JSON = "Accept: application/json"
        private const val HEADER_CONTENT_TYPE_JSON = "Content-Type: application/json"

        private lateinit var instance: RetrofitRestRepository

        /**
         * Build if it is necessary create RetrofitRestRepository or existing instance
         *
         * @return RetrofitRestRepository implementation instance
         */
        fun getInstance(): RetrofitRestRepository {

            if (!::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitRestRepository::class.java)
            }

            return instance
        }
    }

    @GET("character")
    @Headers(HEADER_ACCEPT_JSON, HEADER_CONTENT_TYPE_JSON)
    override suspend fun getCharactersAtPage(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    @Headers(HEADER_ACCEPT_JSON, HEADER_CONTENT_TYPE_JSON)
    override suspend fun getCharacterById(@Path("id") id: String): CharacterResponse

    //Add more Rick&Morty universe endpoints.
}
