package com.jjeanjacques.rinhabackend.entity;


import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "transacoes")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: ClientEntity? = null,

    @Column(nullable = false)
    val valor: Int = 0,

    @Column(nullable = false, length = 1)
    val tipo: String = "",

    @Column(nullable = false, length = 10)
    val descricao: String = "",

    @Column(name = "realizada_em", nullable = false)
    val realizadaEm: LocalDateTime = LocalDateTime.now()
)
