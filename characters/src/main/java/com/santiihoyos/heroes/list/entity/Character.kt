package com.santiihoyos.heroes.list.entity

/**
 * Entity to represent some character of Rick&Morty universe
 *
 */
data class Character(

    /**
     * The id of the character.
     */
    val id: Int,

    /**
     * The name of the character.
     */
    val name: String,

    /**
     * The status of the character
     */
    val status: Status,

    /**
     * The species of the character.
     */
    val species: String,

    /**
     * The type or subspecies of the character.
     */
    val type: String,

    /**
     * The gender of the character.
     */
    val gender: Gender,

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
) {

    enum class Status {
        ALIVE, DEAD, UNKNOWN
    }

    enum class Gender {
        FEMALE, MALE, GENDERLESS, UNKNOWN
    }
}
