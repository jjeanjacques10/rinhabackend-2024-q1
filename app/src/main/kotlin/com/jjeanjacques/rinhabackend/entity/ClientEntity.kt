package com.jjeanjacques.rinhabackend.entity

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 50)
    val nome: String = "",

    @Column(nullable = false)
    val limite: Int = 0
)
