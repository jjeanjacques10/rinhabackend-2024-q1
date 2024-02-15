package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.model.TransactionResult
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
class TransactionQueryRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Query(nativeQuery = true, value = "SELECT * FROM debitar(:clienteId, :valor, :descricao)")
    fun debitar(
        clienteId: Int,
        valor: Int,
        descricao: String
    ): TransactionResult? {
        val query = entityManager.createNativeQuery(
            "SELECT * FROM debitar(:clienteId, :valor, :descricao)"
        )

        query.setParameter("clienteId", clienteId)
        query.setParameter("valor", valor)
        query.setParameter("descricao", descricao)

        val result = query.resultList
        return if (result.isNotEmpty()) {
            val row = result[0] as Array<*>
            TransactionResult(
                row[0] as Int,
                row[1] as Boolean,
                row[2] as String,
            )
        } else {
            null
        }
    }

    fun creditar(
        clienteId: Int,
        valor: Int,
        descricao: String
    ): TransactionResult? {
        val query = entityManager.createNativeQuery(
            "SELECT * FROM creditar(:clienteId, :valor, :descricao)"
        )

        query.setParameter("clienteId", clienteId)
        query.setParameter("valor", valor)
        query.setParameter("descricao", descricao)

        val result = query.resultList
        return if (result.isNotEmpty()) {
            val row = result[0] as Array<*>
            TransactionResult(
                row[0] as Int,
                row[1] as Boolean,
                row[2] as String,
            )
        } else {
            null
        }
    }
}
