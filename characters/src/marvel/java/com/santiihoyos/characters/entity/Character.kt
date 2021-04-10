package com.santiihoyos.characters.entity

/**
 * Entity to represent some character of Rick&Morty universe
 *
 */
data class Character(

    /**
     * The unique ID of the character resource
     */
    val id: Int?,

    /**
     * The name of the character
     */
    val name: String?,

    /**
     * A short bio or description of the character
     */
    val description: String?,

    /**
     * The base image url.
     * With this url image can not be loaded directly
     */
    private val imageUrl: String?,

    /**
     * File extension of image
     */
    private val imageExtension: String?
) {

    /**
     * Resolve image url for 200x200px used in lists for example to avoid overflow memory
     *
     * @return String with public image url
     */
    fun getPreviewImageUrlFor(): String? = imageUrl?.let { _imageUrl ->

        return@let imageExtension?.let { _imageExtension -> "$_imageUrl./standard_xlarge.$_imageExtension" }
    }
}
