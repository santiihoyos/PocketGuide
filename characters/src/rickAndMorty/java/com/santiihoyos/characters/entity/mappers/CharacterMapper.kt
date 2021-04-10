package com.santiihoyos.characters.entity.mappers

import com.santiihoyos.base_api.Mapper
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import javax.inject.Inject

const val STATUS_ALIVE = "Alive"
const val STATUS_DEAD = "Dead"
//const val STATUS_UNKNOWN = "unknown"
const val GENDER_FEMALE = "Female"
const val GENDER_MALE = "Male"
const val GENDER_GENDERLESS = "Genderless"
//const val GENDER_UNKNOWN = "unknown"

/**
 * CharacterResponse to Character entity
 */
class CharacterMapper @Inject constructor(
    private val locationMapper: LocationMapper
) : Mapper<CharacterResponse, Character> {

    override fun map(originEntity: CharacterResponse) = Character(
        id = originEntity.id,
        name = originEntity.name,
        status = mapStatus(originEntity.status),
        species = originEntity.species,
        type = originEntity.type,
        gender = mapGender(originEntity.gender),
        origin = originEntity.origin,
        location = originEntity.location?.let(locationMapper::map),
        image = originEntity.image,
        episode = originEntity.episode
    )

    /**
     * Resolve character status
     */
    private fun mapStatus(characterStatus: String): Character.Status = when (characterStatus) {
        STATUS_ALIVE -> Character.Status.ALIVE
        STATUS_DEAD -> Character.Status.DEAD
        else -> Character.Status.UNKNOWN
    }


    /**
     * Resolves character's gender
     */
    private fun mapGender(characterGender: String): Character.Gender = when (characterGender) {
        GENDER_FEMALE -> Character.Gender.FEMALE
        GENDER_MALE -> Character.Gender.MALE
        GENDER_GENDERLESS -> Character.Gender.GENDERLESS
        else -> Character.Gender.UNKNOWN
    }
}
