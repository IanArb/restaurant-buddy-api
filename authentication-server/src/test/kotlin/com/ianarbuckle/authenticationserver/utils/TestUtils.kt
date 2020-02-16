package com.ianarbuckle.authenticationserver.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.ianarbuckle.authenticationserver.authentication.model.AuthBody


/**
 * @author ianarbuckle on 28/05/2018.
 */
object TestUtils {

    fun createAuthUser(): AuthBody = AuthBody("1234-1234-1234-1234", "ian@mail.com", "password", false)

    fun createUser(): AuthBody = AuthBody("1234-1234-1234-1234", "ian.arbuckle@mail.com", "password", false)

    fun asJsonString(obj: Any): String {
        try {
            val mapper = ObjectMapper()
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            return mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}