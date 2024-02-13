package com.jjeanjacques.rinhabackend.controller

import com.jjeanjacques.rinhabackend.controller.model.ResponseDto
import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.model.FinancialStatement
import com.jjeanjacques.rinhabackend.service.FinancialStatementService
import com.jjeanjacques.rinhabackend.service.TransactionService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/clientes")
@Validated
class Controller(
    val transactionService: TransactionService,
    val financialStatementService: FinancialStatementService
) {

    @PostMapping("/{clientId}/transacoes")
    fun createClient(
        @PathVariable clientId: Int,
        @Valid @RequestBody transaction: TransactionDto
    ): ResponseEntity<ResponseDto> {
        log.debug("Processing transaction to client $clientId")
        val balance = transactionService.save(clientId, transaction)
        return ResponseEntity.ok(
            ResponseDto(
                balance.valor,
                balance.cliente!!.limite
            )
        )
    }

    @GetMapping("/{clientId}/extrato")
    fun getBalance(
        @PathVariable clientId: Int,
    ): ResponseEntity<FinancialStatement> {
        log.debug("Create financial statement to client $clientId")
        val statement = financialStatementService.getFromUser(clientId)
        return ResponseEntity.ok(
            statement
        )
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}