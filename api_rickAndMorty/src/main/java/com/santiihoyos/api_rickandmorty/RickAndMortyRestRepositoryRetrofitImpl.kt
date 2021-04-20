package com.santiihoyos.api_rickandmorty

import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RickAndMortyRestRepositoryRetrofitImpl : RickAndMortyRestRepository {

    companion object {

        private const val API_URL = "https://rickandmortyapi.com/api/"
        private const val HEADER_ACCEPT_JSON = "Accept: application/json"
        //private const val HEADER_CONTENT_TYPE_JSON = "Content-Type: application/json"

        private lateinit var instance: RickAndMortyRestRepositoryRetrofitImpl

        /**
         * Build if it is necessary create RetrofitRestRepository or existing instance
         *
         * @return RetrofitRestRepository implementation instance
         */
        fun getInstance(): RickAndMortyRestRepositoryRetrofitImpl {

            if (!Companion::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RickAndMortyRestRepositoryRetrofitImpl::class.java)
            }

            return instance
        }


        /**
         * Builds http client for intercept all calls and add required query params.
         * also add some interceptors to log request and response
         *
         * @return OkHttpClient - http client with interceptors
         */
        private fun getOkHttpClient(): OkHttpClient {

            val okHttpBuilder = OkHttpClient.Builder()
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            return okHttpBuilder.build()
        }
    }

    @GET("character")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getCharactersAtPage(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getCharacterById(@Path("id") id: String): CharacterResponse

    //Add more Rick&Morty universe endpoints.
}
