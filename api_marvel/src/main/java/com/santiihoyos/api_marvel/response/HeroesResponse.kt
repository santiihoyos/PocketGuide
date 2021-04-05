package com.santiihoyos.api_marvel.response

import com.santiihoyos.base_api.response.Response

/**
 * Characters LIST response
 */
data class HeroesResponse(

    /**
     * Information about chracters
     */
    val info: Info,

    /**
     *  Characters objects
     */
    val results: List<HeroResponse>,

    ) : Response() {

    /**
     * Entity for Info response
     */
    inner class Info(

        /**
         * Total characters
         */
        val count: Int = 0,

        /**
         * total pages
         */
        val pages: Int = 0,

        /**
         * next page url
         */
        val next: String = "",

        /**
         * prev page url
         */
        val prev: String = ""
    )
}

