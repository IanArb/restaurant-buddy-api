package com.ianarbuckle.restaurantlooker.authentication.service

import com.ianarbuckle.restaurantlooker.authentication.model.Role
import com.ianarbuckle.restaurantlooker.authentication.model.User
import com.ianarbuckle.restaurantlooker.authentication.repository.RoleRepository
import com.ianarbuckle.restaurantlooker.authentication.repository.UserRepository
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

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun findAll(): List<User> = userRepository.findAll()

    fun saveUser(user: User) {
        val roles = user.roles
        roles?.first()?.let { roleRepository.save(it) }
        user.password = passwordEncoder.encode(user.password)
        user.isEnabled = true
//        val userRole = Role(role = "ADMIN")
//        user.roles = HashSet<Role>(listOf(userRole))
        userRepository.save(user)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
        val authorities = getUserAuthority(user.roles!!)
        return buildUserForAuthentication(user, authorities)
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