package com.ianarbuckle.restaurantlooker.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author ianarbuckle on 02/09/2018.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found")
class ResourceNotFoundException : RuntimeException()

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate item")
class DuplicateItemException : RuntimeException()

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
class BadRequestException : RuntimeException()

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
class InternalServerErrorException() : RuntimeException()

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not found")
class UserNotFoundException() : RuntimeException()