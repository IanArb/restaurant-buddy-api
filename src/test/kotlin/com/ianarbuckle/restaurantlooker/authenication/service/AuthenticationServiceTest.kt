package com.ianarbuckle.restaurantlooker.authenication.service

import com.ianarbuckle.restaurantlooker.authentication.repository.AuthenticationRepository
import com.ianarbuckle.restaurantlooker.authentication.service.AuthenticationServiceImpl
import com.ianarbuckle.restaurantlooker.utils.TestUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * @author ianarbuckle on 2019-08-24.
 */
class AuthenticationServiceTest {

    @Mock
    lateinit var repository: AuthenticationRepository

    @InjectMocks
    lateinit var service: AuthenticationServiceImpl

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun `verify that service should create a valid user`() {
        whenever(repository.save(TestUtils.createUser())).thenReturn(TestUtils.createUser())

        service.saveUser(TestUtils.createUser())

        verify(repository, times(1)).save(TestUtils.createUser())
    }

    @Test
    fun `verify that service should find valid user by uuid`() {
        whenever(repository.findAuthenticationByUuid(anyString())).thenReturn(TestUtils.createUser())

        service.findAuthenticationByUuid("124324-1242343-123121")

        verify(repository, times(1)).findAuthenticationByUuid(anyString())
    }

    @Ignore
    @Test
    fun `verify that service should delete user by uuid and is not null`() {
        val user = TestUtils.createUser()

        user.uuid.let { service.deleteAuthenticationByUuid(it) }

        verify(repository, times(1)).delete(any())
    }
}