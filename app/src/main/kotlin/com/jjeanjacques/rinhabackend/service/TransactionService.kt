package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.controller.model.BalanceDto
import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.exception.InvalidBalanceException
import com.jjeanjacques.rinhabackend.model.ClientWithBalance
import com.jjeanjacques.rinhabackend.model.TransactionResult
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionQueryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TransactionService(
    val clientRepository: ClientRepository,
    val transactionQueryRepository: TransactionQueryRepository
) {

    fun save(clientId: Int, transaction: TransactionDto): BalanceDto {
        val clientWithBalance = clientRepository.findClienteWithSaldoById(clientId.toLong())
            ?: throw ClientNotFound("Client not found with id $clientId")

        val result = when (transaction.type) {
            TypeTransaction.D -> debit(transaction, clientWithBalance)
            TypeTransaction.C -> credit(transaction, clientWithBalance)
        }

        return BalanceDto(
            limit = clientWithBalance.limit,
            balance = result!!.newBalance
        )
    }

    private fun credit(transaction: TransactionDto, client: ClientWithBalance): TransactionResult? {
        return transactionQueryRepository.creditar(client.clientId.toInt(), transaction.value, transaction.description)
    }

    private fun debit(transaction: TransactionDto, client: ClientWithBalance): TransactionResult? {
        validBalance(transaction, client)
        return transactionQueryRepository.debitar(client.clientId.toInt(), transaction.value, transaction.description)
    }

    private fun validBalance(transaction: TransactionDto, client: ClientWithBalance) {
        if ((client.balanceValue?.minus(transaction.value))!! < -client.limit) throw InvalidBalanceException("Transaction value exceeds client limit")
    }
}