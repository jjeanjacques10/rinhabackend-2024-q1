package com.jjeanjacques.rinhabackend.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BalanceDto(
    @JsonProperty("limite") val limit: Int,
    @JsonProperty("saldo") val balance: Int
)
