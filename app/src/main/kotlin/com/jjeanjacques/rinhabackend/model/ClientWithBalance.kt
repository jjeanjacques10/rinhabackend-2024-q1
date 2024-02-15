package com.jjeanjacques.rinhabackend.model

import com.fasterxml.jackson.annotation.JsonProperty


data class ClientWithBalance(
    @JsonProperty("clienteId")
    var clientId: Long,
    @JsonProperty("nome")
    var name: String,
    @JsonProperty("limite")
    var limit: Int,
    @JsonProperty("saldoId")
    var balanceId: Long?,
    @JsonProperty("saldoValor")
    var balanceValue: Int?
)
