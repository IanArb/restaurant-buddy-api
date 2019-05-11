package com.ianarbuckle.restaurantlooker.exception

import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest

/**
 * @author ianarbuckle on 02/09/2018.
 */
@ControllerAdvice
@RestController
class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFoundException(exception: ResourceNotFoundException, request: WebRequest): ResponseEntity<ErrorBody> {
        val errorBody = exception.message?.let { ErrorBody(LocalDateTime(), it, request.getDescription(false)) }
        return ResponseEntity(errorBody, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(exception: InternalServerErrorException, request: WebRequest) :
            ResponseEntity<ErrorBody> {
        val errorBody = exception.message?.let { ErrorBody(LocalDateTime(), it, request.getDescription(false)) }
        return ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(DuplicateItemException::class)
    fun handleDuplicatedItemException(exception: DuplicateItemException, request: WebRequest) :
            ResponseEntity<ErrorBody> {
        val errorBody = exception.message?.let { ErrorBody(LocalDateTime(), it, request.getDescription(false)) }
        return ResponseEntity(errorBody, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException, request: WebRequest) :
            ResponseEntity<ErrorBody> {
        val errorBody = exception.message?.let { ErrorBody(LocalDateTime(), it, request.getDescription(false)) }
        return ResponseEntity(errorBody, HttpStatus.BAD_REQUEST)
    }
}