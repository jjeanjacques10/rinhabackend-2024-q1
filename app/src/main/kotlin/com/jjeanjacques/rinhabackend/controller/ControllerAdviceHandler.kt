package com.jjeanjacques.rinhabackend.controller

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.jjeanjacques.rinhabackend.controller.model.ExceptionDetailsDTO
import com.jjeanjacques.rinhabackend.exception.ClientNotFound
import com.jjeanjacques.rinhabackend.exception.InvalidBalanceException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.lang.Nullable
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdviceHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(
        ex: Exception,
        @Nullable body: Any?,
        headers: org.springframework.http.HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDetails = ExceptionDetailsDTO(
            title = ex.cause?.message,
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails)
    }

    @ExceptionHandler(ClientNotFound::class)
    fun handleClientNotFoundException(ex: ClientNotFound, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
            title = "Client not found",
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.NOT_FOUND.value(),
            details = ex.message,
            developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails)
    }

    @ExceptionHandler(InvalidBalanceException::class)
    fun handleInvalidBalanceExceptionException(ex: InvalidBalanceException, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            details = ex.message,
            developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails)
    }

    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormatExceptionException(ex: InvalidFormatException, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            details = ex.message,
            developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDetails = ExceptionDetailsDTO(
            title = ex.cause?.message,
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDetails = ExceptionDetailsDTO(
            title = ex.cause?.message,
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails)
    }
}