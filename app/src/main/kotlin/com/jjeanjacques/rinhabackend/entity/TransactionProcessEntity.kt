package com.jjeanjacques.rinhabackend.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class TransactionProcessEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val novo_saldo: Int,
    val possui_erro: Boolean,
    val mensagem: String
)
