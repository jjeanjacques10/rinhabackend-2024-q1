package com.jjeanjacques.rinhabackend.controller

import com.jjeanjacques.rinhabackend.controller.model.ResponseDto
import com.jjeanjacques.rinhabackend.controller.model.TransactionDto
import com.jjeanjacques.rinhabackend.service.TransactionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clientes")
class Controller(
    val transactionService: TransactionService
) {

    @PostMapping("/{clientId}/transacoes")
    fun createClient(
        @PathVariable clientId: Int,
        @RequestBody transaction: TransactionDto
    ): ResponseEntity<ResponseDto> {
        log.info("Processing transaction to client $clientId")
        val balance = transactionService.save(clientId, transaction)
        return ResponseEntity.ok(
            ResponseDto(
                balance.valor,
                balance.cliente!!.limite
            )
        )
    }

    val log: Logger = LoggerFactory.getLogger(this::class.java)
}