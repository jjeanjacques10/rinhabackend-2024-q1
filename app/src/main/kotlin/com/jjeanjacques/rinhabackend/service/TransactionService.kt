package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.entity.BalanceEntity
import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.repository.BalanceRepository
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionRepository
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.exception.InvalidBalanceException
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

    fun save(clientId: Int, transaction: TransactionDto): BalanceEntity {
        log.info("Saving transaction to database")

        val client = clientRepository.findById(clientId.toLong())
            .orElseThrow { ClientNotFound("Client not found with id $clientId") }

        validBalance(transaction, client.limite)

        val transactionEntity = TransactionEntity(
            valor = transaction.value,
            descricao = transaction.description,
            tipo = transaction.type.toString().lowercase(Locale.getDefault()),
            cliente = client,
            realizadaEm = LocalDateTime.now()
        )

        transactionRepository.save(transactionEntity)

        return balanceRepository.findByCliente(client)
    }

    private fun validBalance(transaction: TransactionDto, limite: Int) {
        when (transaction.type) {
            TypeTransaction.D -> if (transaction.value > limite) throw InvalidBalanceException("Transaction value exceeds client limit")
            TypeTransaction.C -> return
        }
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}