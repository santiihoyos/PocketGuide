package com.santiihoyos.base_api

import com.santiihoyos.base_api.response.Response

/**
 * Mapper interface, defines standard mapper of app
 */
fun interface Mapper<OE : Response, EE> {

    fun map(originEntity: OE): EE

    fun map(originEntities: List<OE>): List<EE> = originEntities.mapNotNull(::map)
}
