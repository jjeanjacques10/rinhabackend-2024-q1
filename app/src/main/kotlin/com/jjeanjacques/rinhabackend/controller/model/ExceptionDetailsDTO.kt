package com.jjeanjacques.rinhabackend.controller.model

data class ExceptionDetailsDTO(
    var title: String? = null,
    val timestamp: String,
    val status: Int,
    val details: String?,
    val developerMethod: String
)
