package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long> {
}