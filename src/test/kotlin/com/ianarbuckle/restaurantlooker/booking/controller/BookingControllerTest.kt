package com.ianarbuckle.restaurantlooker.booking.controller

import com.ianarbuckle.restaurantlooker.booking.service.BookingService
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Mockito.`when` as whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * @author ianarbuckle on 07/05/2019.
 */
class BookingControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: BookingService

    @InjectMocks
    lateinit var controller: BookingController

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `verify that retrieve booking call should return list of bookings`() {
        whenever(service.findAllBookings()).thenReturn(TestUtils.buildBookingsModel())

        mockMvc.perform(MockMvcRequestBuilders.get("/booking").accept(MediaType.APPLICATION_JSON))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect { MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) }

        verify(service, times(1)).findAllBookings()
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that retrieve booking by uuid should return expected list of bookings for that owner`() {
        whenever(service.findBookingsByUUID("24345-34534-34534")).thenReturn(TestUtils.buildBookingsModel())

        mockMvc.perform(MockMvcRequestBuilders.get("/booking" + "/{uuid}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect { MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) }

        verify(service, times(1)).findBookingsByUUID(anyString())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify when created booking that it should not be empty or return bad request`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/booking", 1)
                .content(TestUtils.asJsonString(TestUtils.createBooking()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        verify(service, times(1)).saveBooking(TestUtils.createBooking())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that update booking call should return updated booking`() {
        whenever(service.findBookingById("1")).thenReturn(TestUtils.createBooking())

        mockMvc.perform(MockMvcRequestBuilders.put("/booking", 1)
                .content(TestUtils.asJsonString(TestUtils.createBooking()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(service, times(1)).updateBooking(TestUtils.createBooking())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `verify that booking is deleted by id`() {
        whenever(service.deleteBookingById("1")).thenReturn(TestUtils.createBooking())

        mockMvc.perform(MockMvcRequestBuilders.delete("/booking" + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(service, times(1)).deleteBookingById(anyString())
        verifyNoMoreInteractions(service)
    }

}