package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.cliente.id = :clientId ORDER BY t.realizadaEm DESC")
    fun findByClientIdOrderByRealizadaEmDesc(
        @Param("clientId") clientId: Long,
        pageable: Pageable
    ): List<TransactionEntity>
}