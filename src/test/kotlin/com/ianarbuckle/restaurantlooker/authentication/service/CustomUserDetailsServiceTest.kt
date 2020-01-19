package com.ianarbuckle.restaurantlooker.authentication.service

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurantlooker.authentication.model.Role
import com.ianarbuckle.restaurantlooker.authentication.model.User
import com.ianarbuckle.restaurantlooker.authentication.repository.RoleRepository
import com.ianarbuckle.restaurantlooker.authentication.repository.UserRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author ianarbuckle on 26/09/2019.
 */
class CustomUserDetailsServiceTest {

    @Mock
    lateinit var repository: UserRepository

    @Mock
    lateinit var roleRepository: RoleRepository

    @Mock
    lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    var userService = CustomUserDetailsService()

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun `verify that find user by email returns valid user by email`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))

        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)
        whenever(repository.findByUuid(anyString())).thenReturn(user)

        val findUserByEmail = userService.findUserByUuid(anyString())
        assertThat(findUserByEmail).isEqualTo(user)
        assertThat(findUserByEmail?.email).isEqualTo("ian@mail.com")
        assertThat(findUserByEmail?.username).isEqualTo("ian")
        assertThat(findUserByEmail?.password).isEqualTo("password")
        assertThat(findUserByEmail?.isEnabled).isTrue()
        assertThat(findUserByEmail?.roles).isEqualTo(roles)
    }

    @Test
    fun `verify that find all returns one user`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)

        val users = ArrayList<User>()
        users.add(user)

        whenever(repository.findAll()).thenReturn(users)
        whenever(roleRepository.save(Role("1", "ADMIN"))).thenReturn(Role("1", "ADMIN"))

        assertThat(userService.findAll()).isNotEmpty()
        assertThat(userService.findAll().size).isEqualTo(1)
    }

    @Test
    fun `verify that saved user is not null or empty`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)

        whenever(repository.save(user)).thenReturn(user)

        assertThat(userService.saveUser(user)).isNotNull()
    }

    @Test
    fun `verify that user is loaded by username`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)
        whenever(repository.findByUuid(anyString())).thenReturn(user)

        assertThat(userService.loadUserByUsername(anyString())).isNotNull()
    }
}