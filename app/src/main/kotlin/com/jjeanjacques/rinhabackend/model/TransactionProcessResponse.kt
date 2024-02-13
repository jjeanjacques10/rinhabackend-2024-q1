package com.jjeanjacques.rinhabackend.model


data class TransactionProcessResponse(
    val novo_saldo: Int,
    val possui_erro: Boolean,
    val mensagem: String
)
