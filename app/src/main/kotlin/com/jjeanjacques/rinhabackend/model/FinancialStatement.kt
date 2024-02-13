package com.jjeanjacques.rinhabackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import java.time.LocalDateTime


data class FinancialStatement(
    @JsonProperty("saldo") val balance: Saldo,
    @JsonProperty("ultimas_transacoes")
    val lastTransactions: List<Transaction>
)

data class Transaction(
    @JsonProperty("valor") val value: Int,
    @JsonProperty("tipo") val type: TypeTransaction,
    @JsonProperty("descricao") val description: String,
    @JsonProperty("realizada_em") val createAt: LocalDateTime
)

data class Saldo(
    val total: Int,
    @JsonProperty("data_extrato")
    val dataExtrato: LocalDateTime,
    val limite: Int
)

