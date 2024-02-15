package com.jjeanjacques.rinhabackend.controller

import com.jjeanjacques.rinhabackend.controller.dto.BalanceDto
import com.jjeanjacques.rinhabackend.controller.dto.TransactionDto
import com.jjeanjacques.rinhabackend.model.FinancialStatement
import com.jjeanjacques.rinhabackend.service.FinancialStatementService
import com.jjeanjacques.rinhabackend.service.TransactionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/clientes")
@Validated
class ControllerTransaction(
    val transactionService: TransactionService,
    val financialStatementService: FinancialStatementService
) {

    @PostMapping("/{clientId}/transacoes")
    fun createClient(
        @PathVariable clientId: Int,
        @Valid @RequestBody transaction: TransactionDto
    ): ResponseEntity<BalanceDto> {
        return ResponseEntity.ok(transactionService.save(clientId, transaction))
    }

    @GetMapping("/{clientId}/extrato")
    fun getBalance(
        @PathVariable clientId: Int,
    ): ResponseEntity<FinancialStatement> {
        val statement = financialStatementService.getFromUser(clientId)
        return ResponseEntity.ok(
            statement
        )
    }
}