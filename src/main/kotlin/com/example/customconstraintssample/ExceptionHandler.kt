package com.example.customconstraintssample

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        req: WebRequest
    ): ResponseEntity<Any>? {
        val message = ex.allErrors.joinToString("\n") { it.defaultMessage?: "" }

        return handleExceptionInternal(
            ex,
            mapOf("errorMessage" to message),
            headers,
            status,
            req,
        )
    }
}
