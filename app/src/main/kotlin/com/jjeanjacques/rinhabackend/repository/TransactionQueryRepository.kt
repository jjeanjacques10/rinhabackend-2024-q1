package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import jakarta.persistence.Tuple
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TransactionQueryRepository : JpaRepository<TransactionEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM debitar(:clienteId, :valor, :descricao)")
    fun debitar(
        @Param("clienteId") clienteId: Int,
        @Param("valor") valor: Int,
        @Param("descricao") descricao: String
    ): Tuple

    @Query(nativeQuery = true, value = "SELECT * FROM creditar(:clienteId, :valor, :descricao)")
    fun creditar(
        @Param("clienteId") clienteId: Int,
        @Param("valor") valor: Int,
        @Param("descricao") descricao: String
    ): Tuple
}
