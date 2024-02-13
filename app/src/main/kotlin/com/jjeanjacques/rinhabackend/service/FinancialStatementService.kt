package com.jjeanjacques.rinhabackend.service

import com.jjeanjacques.rinhabackend.entity.BalanceEntity
import com.jjeanjacques.rinhabackend.entity.TransactionEntity
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.model.FinancialStatement
import com.jjeanjacques.rinhabackend.model.Saldo
import com.jjeanjacques.rinhabackend.model.Transaction
import com.jjeanjacques.rinhabackend.repository.BalanceRepository
import com.jjeanjacques.rinhabackend.repository.ClientRepository
import com.jjeanjacques.rinhabackend.repository.TransactionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class FinancialStatementService(
    val transactionRepository: TransactionRepository,
    val clientRepository: ClientRepository,
    val balanceRepository: BalanceRepository
) {

    fun getFromUser(clientId: Int): FinancialStatement {
        val client = clientRepository.findById(clientId.toLong())
            .orElseThrow { ClientNotFound("Client not found with id $clientId") }
        val balance = balanceRepository.findByCliente(client)
        val transactions = transactionRepository.findByCliente(client, PageRequest.of(0, 10))

        return buildFinancialStatement(transactions, balance)
    }

    private fun buildFinancialStatement(
        transactions: List<TransactionEntity>,
        balance: BalanceEntity
    ): FinancialStatement {
        return FinancialStatement(
            balance = Saldo(
                total = balance.valor,
                dataExtrato = LocalDateTime.now(),
                limite = balance.cliente!!.limite
            ),
            lastTransactions = transactions.map {
                Transaction(
                    value = it.valor,
                    type = TypeTransaction.valueOf(it.tipo.uppercase()),
                    description = it.descricao,
                    createAt = it.realizadaEm
                )
            }.sortedByDescending { it.createAt }
        )
    }

}