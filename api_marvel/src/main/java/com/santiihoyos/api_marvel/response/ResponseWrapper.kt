package com.santiihoyos.api_marvel.response

import com.google.gson.annotations.SerializedName
import com.santiihoyos.base_api.response.RestResponse

open class ResponseWrapper<T>(

    /**
     * The HTTP status code of the returned result
     */
    @SerializedName("code")
    final override var httpCode: Int = -1,

    /**
     * A string description of the call status
     */
    @SerializedName("status")
    final override var message: String? = "",

    /**
     * A digest value of the content
     */
    val eta: String = "",

    /**
     * The results returned by the call
     */
    val data: ResponseContainer<T>? = null,

    /**
     * The copyright notice for the returned result
     */
    val copyright: String? = null,

    /**
     * The attribution notice for this result
     */
    val attributionText: String? = null,

    /**
     * An HTML representation of the attribution notice for this result
     */
    @SerializedName("attributionHTML")
    val attributionHtml: String? = null

) : RestResponse()
