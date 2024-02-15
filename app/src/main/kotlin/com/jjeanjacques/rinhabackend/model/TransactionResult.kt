package com.jjeanjacques.rinhabackend.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TransactionResult(
    @JsonProperty("novo_saldo")
    var newBalance: Int,
    @JsonProperty("possui_erro")
    var hasError: Boolean,
    @JsonProperty("mensagem")
    var message: String
)