package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.CharacterResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

internal interface MarvelRestRepositoryRetrofitImpl : MarvelRestRepository {

    @GET("/v1/public/characters")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getCharactersByPage(
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharacterResponse

    @GET("/v1/public/characters/{id}")
    @Headers(HEADER_ACCEPT_JSON)
    override suspend fun getCharactersById(@Path("id") id: String): CharacterResponse?

    //Add more Rick&Morty universe endpoints.

    companion object {

        private const val API_URL = "https://gateway.marvel.com:443"
        private const val HEADER_ACCEPT_JSON = "Accept: application/json"
        private const val HEADER_CONTENT_TYPE_JSON = "Content-Type: application/json"
        private const val API_KEY_PARAM_NAME = "apikey"

        private lateinit var instance: MarvelRestRepositoryRetrofitImpl

        /**
         * Build if it is necessary create RetrofitRestRepository or existing instance
         *
         * @return RetrofitRestRepository implementation instance
         */
        fun getInstance(
            apiKey: String,
            privateKey: String
        ): MarvelRestRepositoryRetrofitImpl {

            if (!Companion::instance.isInitialized) {
                instance = Retrofit.Builder()
                    .client(getOkHttpClient(apiKey, privateKey))
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MarvelRestRepositoryRetrofitImpl::class.java)
            }

            return instance
        }

        /**
         * Builds http client for intercept all calls and add apiKey param.
         * also add some interceptors to log request and response
         *
         * @return OkHttpClient - http client with interceptors
         */
        private fun getOkHttpClient(
            apiKey: String,
            privateKey: String
        ): OkHttpClient {

            val okHttpBuilder = OkHttpClient.Builder()
            okHttpBuilder.addInterceptor(getAuthInterceptor(apiKey, privateKey))
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            return okHttpBuilder.build()
        }

        private fun getAuthInterceptor(
            apiKey: String,
            privateKey: String
        ): Interceptor = Interceptor { chain ->

            val ts = Date().time.toString()
            val hash =  md5(ts + privateKey + apiKey)
            val request = chain.request().newBuilder()
            val url = chain.request().url.newBuilder()
            url.addQueryParameter(API_KEY_PARAM_NAME, apiKey)
            url.addQueryParameter("ts", ts)
            url.addQueryParameter("hash", hash)
            request.url(url.build())
            chain.proceed(request.build())
        }


        fun md5(stringToHash: String): String {
            val md5 = "MD5"

            try {
                val digest = MessageDigest.getInstance(md5)
                digest.update(stringToHash.toByteArray())
                val messageDigest = digest.digest()

                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) {
                        h = "0$h"
                    }
                    hexString.append(h)
                }
                return hexString.toString()

            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return ""
        }
    }

}
