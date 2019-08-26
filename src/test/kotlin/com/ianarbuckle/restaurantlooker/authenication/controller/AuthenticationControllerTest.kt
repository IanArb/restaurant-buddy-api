package com.ianarbuckle.restaurantlooker.authenication.controller

import com.ianarbuckle.restaurantlooker.authentication.controller.AuthenticationController
import com.ianarbuckle.restaurantlooker.authentication.service.AuthenticationService
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * @author ianarbuckle on 2019-08-24.
 */
class AuthenticationControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: AuthenticationService

    @InjectMocks
    lateinit var controller: AuthenticationController

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `verify when created user that it should not be empty or return bad request`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/authentication", 1)
                .content(TestUtils.asJsonString(TestUtils.createUser()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        Mockito.verify(service, Mockito.times(1)).saveUser(TestUtils.createUser())
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that retrieve booking by uuid should return expected list of bookings for that owner`() {
        Mockito.`when`(service.findAuthenticationByUuid("24345-34534-34534")).thenReturn(TestUtils.createUser())

        mockMvc.perform(MockMvcRequestBuilders.get("/authentication" + "/{uuid}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect { MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) }

        Mockito.verify(service, Mockito.times(1)).findAuthenticationByUuid(anyString())
        Mockito.verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that user is deleted by id`() {
        Mockito.`when`(service.deleteAuthenticationByUuid("124324-1242343-123121")).thenReturn(TestUtils.createUser())

        mockMvc.perform(MockMvcRequestBuilders.delete("/authentication" + "/{uuid}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.verify(service, times(1)).deleteAuthenticationByUuid(anyString())
        Mockito.verifyNoMoreInteractions(service)
    }
}