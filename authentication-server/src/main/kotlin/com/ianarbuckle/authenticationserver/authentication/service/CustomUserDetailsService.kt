package com.ianarbuckle.authenticationserver.authentication.service

import com.ianarbuckle.authenticationserver.authentication.model.Role
import com.ianarbuckle.authenticationserver.authentication.model.User
import com.ianarbuckle.authenticationserver.authentication.repository.RoleRepository
import com.ianarbuckle.authenticationserver.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author ianarbuckle on 22/09/2019.
 */
@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun findUserByUuid(uuid: String): User? {
        return userRepository.findByUuid(uuid)
    }

    fun findAll(): List<User> = userRepository.findAll()

    fun saveUser(user: User) {
        val roles = user.roles
        roles?.first()?.let { roleRepository.save(it) }
        user.password = passwordEncoder.encode(user.password)
        user.isEnabled = true
        userRepository.save(user)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(uuid: String): UserDetails? {
        val user = userRepository.findByUuid(uuid)
        val authorities = user.roles?.let { getUserAuthority(it) }
        return authorities?.let { buildUserForAuthentication(user, it) }
    }

    private fun getUserAuthority(userRoles: Set<Role>): List<GrantedAuthority> {
        val roles = HashSet<GrantedAuthority>()
        userRoles.forEach { role -> roles.add(SimpleGrantedAuthority(role.role)) }
        return ArrayList(roles)
    }

    private fun buildUserForAuthentication(user: User, authorities: List<GrantedAuthority>): UserDetails {
        return org.springframework.security.core.userdetails.User(user.email, user.password, authorities)
    }
}