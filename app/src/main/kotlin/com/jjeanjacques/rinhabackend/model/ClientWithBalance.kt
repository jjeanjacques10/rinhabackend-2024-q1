package com.jjeanjacques.rinhabackend.model

data class ClientWithBalance(
    val clienteId: Long,
    val nome: String,
    val limite: Int,
    val saldoId: Long?,
    val saldoValor: Int?
)
