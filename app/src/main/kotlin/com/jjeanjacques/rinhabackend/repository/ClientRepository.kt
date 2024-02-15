package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.model.ClientWithBalance
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository


@Repository
class ClientRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun findClienteWithSaldoById(clientId: Long): ClientWithBalance? {
        val query = entityManager.createNativeQuery(
            "SELECT c.id AS clienteId, c.nome, c.limite, s.id AS saldoId, s.valor " +
                    "FROM clientes c " +
                    "LEFT JOIN saldos s ON c.id = s.cliente_id " +
                    "WHERE c.id = :clientId"
        )

        query.setParameter("clientId", clientId)

        val result = query.resultList
        return if (result.isNotEmpty()) {
            val row = result[0] as Array<*>
            ClientWithBalance(
                row[0] as Long,
                row[1] as String,
                row[2] as Int,
                row[3] as Long?,
                row[4] as Int?
            )
        } else {
            null
        }
    }
}