package com.santiihoyos.characters.entity.mappers

import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.base_api.Mapper
import com.santiihoyos.characters.entity.Character
import javax.inject.Inject

/**
 * CharacterResponse to Character entity
 */
class CharacterMapper @Inject constructor() : Mapper<CharacterResponse, List<Character>?> {

    override fun map(originEntity: CharacterResponse): List<Character>? {

        return originEntity.data?.results?.let { _results ->

            return@let _results.map { _character ->

                Character(
                    id = _character.id,
                    name = _character.name,
                    description = _character.description,
                    imageUrl = _character.thumbnail?.path,
                    imageExtension = _character.thumbnail?.extension
                )
            }.toList()
        }
    }
}
