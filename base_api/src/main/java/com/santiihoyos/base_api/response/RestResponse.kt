package com.santiihoyos.base_api.response

open class RestResponse() {

    /**
     *  Http code from server response
     */
    var httpCode: Int = 200

    /**
     * If response was wrong contains error description
     * null if response was ok
     */
    var errorMessage: String? = null

    //All common logic or structure of responses here...

}
