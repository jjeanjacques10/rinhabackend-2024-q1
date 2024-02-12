package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.entity.BalanceEntity
import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import com.jjeanjacques.rinhabackend.repository.BalanceRepository
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionRepository
import main.kotlin.com.jjeanjacques.rinhabackend.exception.ClientNotFound
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val clientRepository: ClientRepository,
    val balanceRepository: BalanceRepository
) {

    fun save(clientId: Int, transactionDto: TransactionDto): BalanceEntity {
        log.info("Saving transaction to database")

        val client = clientRepository.findById(clientId.toLong())
            .orElseThrow { ClientNotFound("Client not found with id $clientId") }

        val transactionEntity = TransactionEntity(
            valor = transactionDto.value,
            descricao = transactionDto.description,
            tipo = transactionDto.type.toString().lowercase(Locale.getDefault()),
            cliente = client,
            realizadaEm = LocalDateTime.now()
        )

        transactionRepository.save(transactionEntity)

        return balanceRepository.findByCliente(client)
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}