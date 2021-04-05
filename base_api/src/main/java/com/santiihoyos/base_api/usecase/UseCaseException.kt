package com.santiihoyos.base_api.usecase

import java.lang.Exception

data class UseCaseException(
    val httpCode: Int? = null,
    override val message: String? = null
) : Exception()
