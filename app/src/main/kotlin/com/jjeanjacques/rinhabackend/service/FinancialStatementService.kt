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
import org.springframework.data.domain.PageRequest
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
            clientWithBalance.clienteId,
            PageRequest.of(0, 10)
        )

        return buildFinancialStatement(transactions, clientWithBalance)
    }

    private fun buildFinancialStatement(
        transactions: List<TransactionEntity>,
        clientWithBalance: ClientWithBalance
    ): FinancialStatement {
        return FinancialStatement(
            balance = Saldo(
                total = clientWithBalance.saldoValor!!,
                dataExtrato = LocalDateTime.now(),
                limite = clientWithBalance.limite
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