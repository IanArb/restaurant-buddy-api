package com.ianarbuckle.restaurantlooker.restaurants.service

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import com.ianarbuckle.restaurantlooker.restaurants.repository.RestaurantRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author ianarbuckle on 28/05/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantServiceTest {

    @Mock
    lateinit var repository: RestaurantRepository

    @InjectMocks
    var service: RestaurantService = RestaurantServiceImpl()

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun `verify that restaurant object should return expected size and is not empty`() {
        whenever(repository.findAll()).thenReturn(TestUtils.buildRestaurantsModel())

        service.findAllRestaurants()

        verify(repository, times(1)).findAll()
        verifyNoMoreInteractions(repository)

        assertThat(service.findAllRestaurants()).isNotEmpty()
        assertThat(service.findAllRestaurants().size).isEqualTo(1)
    }

    @Test
    fun `verify find all restaurants should return expected size and is not empty`() {
        whenever(repository.findAll()).thenReturn(TestUtils.buildRestaurantsModel())

        service.findAllRestaurants()

        verify(repository, times(1)).findAll()
        verifyNoMoreInteractions(repository)

        assertThat(service.findAllRestaurants()).isNotEmpty()
        assertThat(service.findAllRestaurants().size).isEqualTo(1)
    }

    @Test
    fun `verify that when creating restaurant it should return create expected restaurant`() {
        val restaurant = TestUtils.createRestaurant()

        service.saveRestaurant(restaurant)

        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify that when deleting restaurant by id it should delete expected restaurant`() {
        val restaurant = TestUtils.createRestaurant()

        restaurant.id?.let { service.deleteRestaurantsById(it) }

        verify(repository, times(1)).delete(any())
    }

    @Test
    fun `verify that restaurant is updated and is not empty or null`() {
        val restaurant = TestUtils.createRestaurant()

        service.updateRestaurant(restaurant)

        assertThat(restaurant).isNotNull()

        verify(repository, times(1)).save(any())
    }
}