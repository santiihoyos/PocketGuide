package com.santiihoyos.characters.entity.mappers

import com.santiihoyos.api_rickandmorty.response.LocationResponse
import com.santiihoyos.base_api.Mapper
import com.santiihoyos.characters.entity.Location
import javax.inject.Inject

class LocationMapper @Inject constructor() : Mapper<LocationResponse, Location> {

    override fun map(originEntity: LocationResponse) = Location(
        id = originEntity.id ?: -1,
        name = originEntity.name ?: "",
        type = originEntity.type ?: "",
        dimension = originEntity.dimension ?: "",
        residents = originEntity.residents ?: emptyList(),
        url = originEntity.url ?: ""
    )
}
