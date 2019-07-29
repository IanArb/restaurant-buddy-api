package com.ianarbuckle.restaurantlooker.tables.service

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurantlooker.tables.repository.TablesRepository
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations.initMocks

/**
 * @author ianarbuckle on 21/07/2019.
 */
class TablesServiceTest {

    @Mock
    lateinit var repository: TablesRepository

    @InjectMocks
    var service: TablesService = TablesServiceImpl()

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun `verify that tables should return not empty or null`() {
        whenever(repository.findTablesByRestaurantId(anyString())).thenReturn(TestUtils.createTables())

        service.findTablesById(anyString())

        verify(repository, times(1)).findTablesByRestaurantId(anyString())

        assertThat(repository.findTablesByRestaurantId(anyString())).isNotNull()
    }

    @Test
    fun `verify that tables should return one row`() {
        whenever(repository.findTablesByRestaurantId(anyString())).thenReturn(TestUtils.createTables())

        service.findTablesById(anyString())

        assertThat(repository.findTablesByRestaurantId(anyString()).row?.size).isEqualTo(1)
    }

    @Test
    fun `verify that tables is created`() {
        whenever(repository.save(TestUtils.createTables())).thenReturn(TestUtils.createTables())

        service.saveTables(TestUtils.createTables())

        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify that tables is updated and not null`() {
        whenever(repository.findTablesByRestaurantId("1")).thenReturn(TestUtils.createTables())

        service.updateTables(TestUtils.createTables())

        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify that tables is deleted by restaurant id`() {
        whenever(repository.findTablesByRestaurantId("1")).thenReturn(TestUtils.createTables())

        service.deleteTablesById("1")

        verify(repository, times(1)).delete(any())
    }
}