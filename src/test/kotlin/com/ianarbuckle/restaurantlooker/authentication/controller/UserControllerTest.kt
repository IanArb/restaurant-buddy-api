package com.ianarbuckle.restaurantlooker.authentication.controller

import com.ianarbuckle.restaurantlooker.authentication.model.Role
import com.ianarbuckle.restaurantlooker.authentication.model.User
import com.ianarbuckle.restaurantlooker.authentication.service.CustomUserDetailsService
import com.ianarbuckle.restaurantlooker.security.JwtTokenProvider
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anySet
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author ianarbuckle on 26/09/2019.
 */
class UserControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var userService: CustomUserDetailsService

    @Mock
    lateinit var jwtTokenProvider: JwtTokenProvider

    @Mock
    lateinit var authenticationManager: AuthenticationManager

    @InjectMocks
    lateinit var controller: UserController

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }


    @Test
    fun `verify when user log ins then it should token`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "ian", "ian@mail.com", "password", true, roles as Set<Role>?)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByEmail("ian@mail.com")).thenReturn(user)
        whenever(jwtTokenProvider.createToken(anyString(), anySet())).thenReturn("1234566")
        whenever(authenticationManager.authenticate(any())).thenReturn(UsernamePasswordAuthenticationToken("ian", "passowrd"))

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login", 1)
                .content(TestUtils.asJsonString(TestUtils.createAuthUser()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(userService, times(1)).findUserByEmail(anyString())

        verifyNoMoreInteractions(userService)
    }

    @Test
    fun `verify when user registers then it should create username and email profile`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "ian", "ian@mail.com", "password", true, roles as Set<Role>?)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByEmail("ian@mail.com")).thenReturn(user)

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register", 1)
                .content(TestUtils.asJsonString(TestUtils.createUser()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `verify that retrieving user is not empty or null`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "ian", "ian@mail.com", "password", true, roles as Set<Role>?)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByEmail("ian@mail.com")).thenReturn(user)

        mockMvc.perform(MockMvcRequestBuilders.get("/authentication/retrieveUser?email=ian@mail.com", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(userService, times(1)).findUserByEmail("ian@mail.com")
    }
}