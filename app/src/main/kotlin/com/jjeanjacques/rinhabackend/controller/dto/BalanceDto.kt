package com.jjeanjacques.rinhabackend.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BalanceDto(
    @JsonProperty("limite") var limit: Int,
    @JsonProperty("saldo") var balance: Int
)
