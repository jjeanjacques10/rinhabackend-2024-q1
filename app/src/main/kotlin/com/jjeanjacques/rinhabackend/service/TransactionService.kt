package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.entity.BalanceEntity
import com.jjeanjacques.rinhabackend.entity.ClientEntity
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.exception.InvalidBalanceException
import com.jjeanjacques.rinhabackend.repository.BalanceRepository
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionQueryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TransactionService(
    val clientRepository: ClientRepository,
    val balanceRepository: BalanceRepository,
    val transactionQueryRepository: TransactionQueryRepository
) {

    fun save(clientId: Int, transaction: TransactionDto): BalanceEntity {
        log.debug("Saving transaction $transaction")

        val client = clientRepository.findById(clientId.toLong())
            .orElseThrow { ClientNotFound("Client not found with id $clientId") }

        when (transaction.type) {
            TypeTransaction.D -> debit(transaction, client)
            TypeTransaction.C -> credit(transaction, client)
        }

        return balanceRepository.findByCliente(client)
    }

    private fun credit(transaction: TransactionDto, client: ClientEntity) {
        transactionQueryRepository.creditar(client.id.toInt(), transaction.value, transaction.description)
    }

    private fun debit(transaction: TransactionDto, client: ClientEntity) {
        validBalance(transaction, client.limite)
        transactionQueryRepository.debitar(client.id.toInt(), transaction.value, transaction.description)
    }

    private fun validBalance(transaction: TransactionDto, limit: Int) {
        if (transaction.value > limit) throw InvalidBalanceException("Transaction value exceeds client limit")
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}