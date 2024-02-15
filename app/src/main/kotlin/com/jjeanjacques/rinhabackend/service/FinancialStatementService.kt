package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.model.ClientWithBalance
import com.jjeanjacques.rinhabackend.model.FinancialStatement
import com.jjeanjacques.rinhabackend.model.Saldo
import com.jjeanjacques.rinhabackend.model.Transaction
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class FinancialStatementService(
    val transactionRepository: TransactionRepository,
    val clientRepository: ClientRepository
) {

    fun getFromUser(clientId: Int): FinancialStatement {
        val clientWithBalance = clientRepository.findClienteWithSaldoById(clientId.toLong())
            ?: throw ClientNotFound("Client not found with id $clientId")
        val transactions = transactionRepository.findByClientIdOrderByRealizadaEmDesc(
            clientWithBalance.clientId
        )

        return buildFinancialStatement(transactions, clientWithBalance)
    }

    private fun buildFinancialStatement(
        transactions: List<TransactionEntity>,
        clientWithBalance: ClientWithBalance
    ): FinancialStatement {
        return FinancialStatement(
            balance = Saldo(
                total = clientWithBalance.balanceValue!!,
                dataExtrato = LocalDateTime.now(),
                limit = clientWithBalance.limit
            ),
            lastTransactions = transactions.map {
                Transaction(
                    value = it.valor,
                    type = TypeTransaction.valueOf(it.tipo.uppercase()),
                    description = it.descricao,
                    createAt = it.realizadaEm
                )
            }
        )
    }
}