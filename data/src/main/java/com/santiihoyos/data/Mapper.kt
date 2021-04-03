package com.santiihoyos.data

import com.santiihoyos.data.response.Response

/**
 * Mapper interface, defines standard mapper of app
 */
fun interface Mapper<OE : Response, EE> {

    fun map(originEntity: OE): EE

    fun map(originEntities: List<OE>): List<EE> = originEntities.mapNotNull(::map)
}
