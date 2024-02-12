package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.BalanceEntity
import com.jjeanjacques.rinhabackend.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BalanceRepository : JpaRepository<BalanceEntity, Long> {
    fun findByCliente(client: ClientEntity): BalanceEntity
}