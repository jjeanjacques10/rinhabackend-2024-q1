package com.jjeanjacques.rinhabackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import java.time.LocalDateTime


data class FinancialStatement(
    @JsonProperty("saldo") val balance: Saldo,
    @JsonProperty("ultimas_transacoes")
    var lastTransactions: List<Transaction>
)

data class Transaction(
    @JsonProperty("valor") var value: Int,
    @JsonProperty("tipo") var type: TypeTransaction,
    @JsonProperty("descricao") var description: String,
    @JsonProperty("realizada_em") var createAt: LocalDateTime
)

data class Saldo(
    var total: Int,
    @JsonProperty("data_extrato")
    var dataExtrato: LocalDateTime,
    @JsonProperty("limite")
    var limit: Int
)

