package com.nuvelance.rectangles.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RectangleExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Any?> {
        val error = mutableListOf<String>("Rectangle Not Found", ex.localizedMessage)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<Any?> {
        val error = mutableListOf<String>("Server Error", ex.localizedMessage)
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}