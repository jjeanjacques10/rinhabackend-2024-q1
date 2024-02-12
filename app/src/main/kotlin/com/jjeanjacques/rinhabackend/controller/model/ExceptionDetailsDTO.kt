package com.jjeanjacques.rinhabackend.controller.model

data class ExceptionDetailsDTO(
    val title: String?,
    val timestamp: String,
    val status: Int,
    val details: String?,
    val developerMethod: String
)
