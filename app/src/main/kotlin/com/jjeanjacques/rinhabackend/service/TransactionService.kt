package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.controller.model.BalanceDto
import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.exception.InvalidBalanceException
import com.jjeanjacques.rinhabackend.model.ClientWithBalance
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionQueryRepository
import jakarta.persistence.Tuple
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TransactionService(
    val clientRepository: ClientRepository,
    val transactionQueryRepository: TransactionQueryRepository
) {

    fun save(clientId: Int, transaction: TransactionDto): BalanceDto {
        log.debug("Saving transaction $transaction")

        val clientWithBalance = clientRepository.findClienteWithSaldoById(clientId.toLong())
            ?: throw ClientNotFound("Client not found with id $clientId")

        val result = when (transaction.type) {
            TypeTransaction.D -> debit(transaction, clientWithBalance)
            TypeTransaction.C -> credit(transaction, clientWithBalance)
        }

        return BalanceDto(
            limit = clientWithBalance.limite,
            balance = result.get(0) as Int
        )
    }

    private fun credit(transaction: TransactionDto, client: ClientWithBalance): Tuple {
        return transactionQueryRepository.creditar(client.clienteId.toInt(), transaction.value, transaction.description)
    }

    private fun debit(transaction: TransactionDto, client: ClientWithBalance): Tuple {
        validBalance(transaction, client)
        return transactionQueryRepository.debitar(client.clienteId.toInt(), transaction.value, transaction.description)
    }

    private fun validBalance(transaction: TransactionDto, client: ClientWithBalance) {
        if ((client.saldoValor?.minus(transaction.value))!! < -client.limite) throw InvalidBalanceException("Transaction value exceeds client limit")
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}