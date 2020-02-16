package com.ianarbuckle.authenticationserver.authentication.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 21/09/2019.
 */
@JsonIgnoreProperties(value = ["password", "isEnabled"])
@Document
data class User(@Id val id: String? = null,
                val uuid: String? = null,
                val username: String? = null,
                val email: String? = null,
                var password: String? = null,
                var isEnabled: Boolean? = null,
                var roles: Set<Role>? = null)

@Document
data class Role(@Id val id: String? = null, var role: String? = null)

data class AuthBody(val uuid: String,
                    val email: String,
                    val password: String,
                    val isRefresh: Boolean)