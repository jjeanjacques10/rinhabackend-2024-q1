package com.jjeanjacques.rinhabackend.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TransactionDto(
        @JsonProperty("valor") val value: Int,
        @JsonProperty("tipo") val type: String,
        @JsonProperty("descricao") val description: String
)
