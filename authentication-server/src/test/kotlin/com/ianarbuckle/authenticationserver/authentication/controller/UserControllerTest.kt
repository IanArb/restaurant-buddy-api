package com.ianarbuckle.authenticationserver.authentication.controller

import com.ianarbuckle.authenticationserver.authentication.model.Role
import com.ianarbuckle.authenticationserver.authentication.model.User
import com.ianarbuckle.authenticationserver.authentication.service.CustomUserDetailsService
import com.ianarbuckle.authenticationserver.security.JwtTokenProvider
import com.ianarbuckle.authenticationserver.utils.TestUtils
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
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByUuid("ian@mail.com")).thenReturn(user)
        whenever(jwtTokenProvider.createToken(anyString(), anySet())).thenReturn("1234566")
        whenever(authenticationManager.authenticate(any())).thenReturn(UsernamePasswordAuthenticationToken("ian", "passowrd"))

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login", 1)
                .content(TestUtils.asJsonString(TestUtils.createAuthUser()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(userService, times(1)).findUserByUuid(anyString())

        verifyNoMoreInteractions(userService)
    }

    @Test
    fun `verify when user registers then it should create username and email profile`() {
        val userRole = Role("1", "ADMIN")
        val roles = HashSet<Role>(listOf(userRole))
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByUuid("ian@mail.com")).thenReturn(user)

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
        val user = User("1", "1234-1234-1234-1234", "ian", "ian@mail.com", "password", true, roles)

        val users = ArrayList<User>()
        users.add(user)

        whenever(userService.findAll()).thenReturn(users)
        whenever(userService.findUserByUuid("ian@mail.com")).thenReturn(user)

        mockMvc.perform(MockMvcRequestBuilders.get("/authentication/retrieveUser?uuid=1234-1234-1234-1234", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(userService, times(1)).findUserByUuid("1234-1234-1234-1234")
    }
}