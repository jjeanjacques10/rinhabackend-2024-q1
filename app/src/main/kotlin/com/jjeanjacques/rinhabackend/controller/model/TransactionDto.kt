package com.jjeanjacques.rinhabackend.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import java.time.LocalDateTime

data class TransactionDto(
    @JsonProperty("valor") val value: Int,
    @JsonProperty("tipo") val type: TypeTransaction,
    @JsonProperty("descricao") val description: String,
    @JsonProperty("realizada_em") val createAt: LocalDateTime?
)
