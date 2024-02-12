package com.jjeanjacques.rinhabackend.entity

import jakarta.persistence.*

@Entity
@Table(name = "saldos")
data class BalanceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: ClientEntity? = null,

    @Column(nullable = false)
    val valor: Int = 0
)
