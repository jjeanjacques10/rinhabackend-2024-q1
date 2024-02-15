package com.jjeanjacques.rinhabackend.controller.dto

data class ExceptionDetailsDTO(
    var title: String? = null,
    var timestamp: String,
    var status: Int,
    var details: String?,
    var developerMethod: String
)
