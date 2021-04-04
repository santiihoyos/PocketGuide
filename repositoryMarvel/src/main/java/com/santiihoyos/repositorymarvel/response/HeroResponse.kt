package com.santiihoyos.repositorymarvel.response

import com.santiihoyos.base_repository.response.Response

data class HeroResponse(

    /**
     * The id of the character.
     */
    val id: String,

    /**
     * The name of the character.
     */
    val name: String,

    /**
     * The status of the character ('Alive', 'Dead' or 'unknown')
     */
    val status: String,

    /**
     * The species of the character.
     */
    val species: String,

    /**
     * The type or subspecies of the character.
     */
    val type: String,

    /**
     * The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
     */
    val gender: String,

    /**
     * Name and link to the character's origin location.
     */
    val origin: Any,

    /**
     * Name and link to the character's last known location endpoint.
     */
    val location: Any,

    /**
     * String (url)	Link to the character's image.
     * All images are 300x300px and most are medium shots or portraits
     * since they are intended to be used as avatars.
     */
    val image: String,

    /**
     * List of episodes in which this character appeared.
     */
    val episode: List<String>,

    /**
     * (url) Link to the character's own URL endpoint.
     */
    val url: String,

    /**
     *  Time at which the character was created in the database.
     */
    val created: String

): Response()
