package com.jjeanjacques.rinhabackend.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime


data class TransactionDto(
    @JsonProperty("valor")
    val value: Number,

    @JsonProperty("tipo")
    val type: TypeTransaction,

    @JsonProperty("descricao")
    @field:Size(
        min = 1,
        max = 10,
        message = "O campo 'descricao' deve ter entre 1 e 10 caracteres"
    )
    @field:NotBlank(message = "A descrição não pode estar em branco.")
    val description: String,
    @JsonProperty("realizada_em")
    val createAt: LocalDateTime?
)
