package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.TransactionProcessEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TransactionQueryRepository : JpaRepository<TransactionProcessEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM debitar(:clienteId, :valor, :descricao)")
    fun debitar(
        @Param("clienteId") clienteId: Int,
        @Param("valor") valor: Int,
        @Param("descricao") descricao: String
    ): Any?

    @Query(nativeQuery = true, value = "SELECT * FROM creditar(:clienteId, :valor, :descricao)")
    fun creditar(
        @Param("clienteId") clienteId: Int,
        @Param("valor") valor: Int,
        @Param("descricao") descricao: String
    ): Any?
}
