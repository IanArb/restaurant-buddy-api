package com.ianarbuckle.restaurantlooker.restaurants.controller

import com.ianarbuckle.restaurantlooker.RestaurantLookerApplication
import com.ianarbuckle.restaurantlooker.restaurants.service.RestaurantService
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.mockito.Mockito.`when` as whenever

/**
 * @author ianarbuckle on 28/05/2018.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RestaurantLookerApplication::class])
@SpringBootTest
class RestaurantControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: RestaurantService

    @InjectMocks
    lateinit var controller: RestaurantController

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `verify that when created should return restaurants`() {
        whenever(service.findAllRestaurants()).thenReturn(TestUtils.buildRestaurantsModel())

        mockMvc.perform(get("/restaurants").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        verify(service, times(1)).findAllRestaurants()
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that delete restaurant by id should delete expected restaurant`() {
        whenever(service.deleteRestaurantsById("1")).thenReturn(TestUtils.createRestaurant())

        mockMvc.perform(delete("/restaurants" + "/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        verify(service).deleteRestaurantsById(anyString())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that find all restaurants should not return bad request`() {
        whenever(service.findAllRestaurants()).thenReturn(TestUtils.buildRestaurantsModel())

        mockMvc.perform(post("/restaurants", 1)
                .content(TestUtils.asJsonString(TestUtils.buildRestaurantsModel()[0]))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)

        verify(service).saveRestaurant(TestUtils.buildRestaurantsModel()[0])
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that update restaurants should not return bad request`() {
        whenever(service.findAllRestaurants()).thenReturn(TestUtils.buildRestaurantsModel())

        mockMvc.perform(put("/restaurants", 1)
                .content(TestUtils.asJsonString(TestUtils.buildRestaurantsModel()[0]))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)

        verify(service).updateRestaurant(TestUtils.buildRestaurantsModel()[0])
        verifyNoMoreInteractions(service)
    }

}