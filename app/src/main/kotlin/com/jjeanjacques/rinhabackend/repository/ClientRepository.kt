package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.ClientEntity
import com.jjeanjacques.rinhabackend.model.ClientWithBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<ClientEntity, Long> {
    @Query(value = "SELECT new com.jjeanjacques.rinhabackend.model.ClientWithBalance(c.id, c.nome, c.limite, s.id as saldoId, s.valor) FROM ClientEntity c INNER JOIN BalanceEntity s on c.id = s.cliente.id WHERE c.id = :clientId")
    fun findClienteWithSaldoById(@Param("clientId") clientId: Long): ClientWithBalance?
}