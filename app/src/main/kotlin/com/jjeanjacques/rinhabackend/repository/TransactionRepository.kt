package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.ClientEntity
import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long> {
    fun findByCliente(client: ClientEntity, pageable: Pageable): List<TransactionEntity>
}