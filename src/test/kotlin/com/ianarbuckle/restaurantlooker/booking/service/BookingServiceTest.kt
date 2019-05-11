package com.ianarbuckle.restaurantlooker.booking.service

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurantlooker.booking.repository.BookingRepository
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations.initMocks

/**
 * @author ianarbuckle on 07/05/2019.
 */
class BookingsServiceTest {

    @Mock
    lateinit var repository: BookingRepository

    @InjectMocks
    var service: BookingService = BookingServiceImpl()

    @Before
    fun setup() {
        initMocks(this)
    }


    @Test
    fun `verify that booking object is not empty or null`() {
        whenever(repository.findAll()).thenReturn(TestUtils.buildBookingsModel())

        assertThat(service.findAllBookings()).isNotEmpty()

        verify(repository, times(1)).findAll()
    }

    @Test
    fun `verify that booking is created and is not empty or null`() {
        whenever(repository.save(TestUtils.createBooking())).thenReturn(TestUtils.createBooking())

        service.saveBooking(TestUtils.createBooking())

        verify(repository, times(1)).save(TestUtils.createBooking())

        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `verify that booking is deleted by expected id`() {
        val booking = TestUtils.createBooking()

        booking.id?.let { service.deleteBookingById(it) }

        verify(repository, times(1)).delete(any())
    }

    @Test
    fun `verify that booking is updated and is not empty or null`() {
        val booking = TestUtils.createBooking()

        whenever(repository.findBookingById("1")).thenReturn(TestUtils.createBooking())

        service.updateBooking(booking)

        assertThat(booking).isNotNull()

        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify when booking is retrieved by uuid returns expected bookings`() {
        whenever(repository.findBookingsByUUID("24345-34534-34534")).thenReturn(TestUtils.buildBookingsModel())

        assertThat(service.findBookingsByUUID("24345-34534-34534")).isNotEmpty()

        verify(repository, times(1)).findBookingsByUUID(anyString())
    }

    @Test
    fun `verify when booking is retrieved by id it should returns expected booking`() {
        whenever(repository.findBookingById("1")).thenReturn(TestUtils.createBooking())

        assertThat(service.findBookingById("1")).isNotNull()

        verify(repository, times(1)).findBookingById(anyString())
    }

}