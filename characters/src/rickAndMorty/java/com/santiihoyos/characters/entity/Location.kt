package com.santiihoyos.characters.entity

data class Location(

    /**
     * The id of the location.
     */
    val id: Int,

    /**
     * The name of the location.
     */
    val name: String = "",

    /**
     * The type of the location.
     */
    val type: String = "",

    /**
     * The dimension in which the location is located.
     */
    val dimension: String = "",

    /**
     * List of character who have been last seen in the location.
     */
    val residents: List<String> = emptyList(),

    /**
     * Link to the location's own endpoint.
     */
    val url: String = ""
)
