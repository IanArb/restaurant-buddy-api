package com.ianarbuckle.restaurantlooker.tables.controller

import com.ianarbuckle.restaurantlooker.RestaurantLookerApplication
import com.ianarbuckle.restaurantlooker.tables.service.TablesService
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as whenever

/**
 * @author ianarbuckle on 21/07/2019.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RestaurantLookerApplication::class])
@SpringBootTest
class TablesControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: TablesService

    @InjectMocks
    lateinit var controller: TablesController

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `verify that retrieve tables by id returns expected tables`() {
        whenever(service.findTablesById("1")).thenReturn(TestUtils.createTables())

        mockMvc.perform(MockMvcRequestBuilders.get("/tables" + "/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect { MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) }

        verify(service, times(1)).findTablesById(anyString())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that when creating tables it should not return bad request`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/tables", 1)
                .content(TestUtils.asJsonString(TestUtils.createTables()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        verify(service, times(1)).saveTables(TestUtils.createTables())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that when updating tables it should return OK response`() {
        whenever(service.findTablesById("1")).thenReturn(TestUtils.createTables())

        mockMvc.perform(MockMvcRequestBuilders.put("/tables", 1)
                .content(TestUtils.asJsonString(TestUtils.createTables()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(service, times(1)).updateTables(TestUtils.createTables())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that tables is deleted by id and returns OK response`() {
        whenever(service.findTablesById("1")).thenReturn(TestUtils.createTables())

        mockMvc.perform(MockMvcRequestBuilders.delete("/tables" + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(service, times(1)).deleteTablesById(anyString())
        verifyNoMoreInteractions(service)
    }


}