package com.jjeanjacques.rinhabackend.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.jjeanjacques.rinhabackend.enums.Type

data class TransactionDto(
    @JsonProperty("valor") val value: Int,
    @JsonProperty("tipo") val type: Type,
    @JsonProperty("descricao") val description: String
)
