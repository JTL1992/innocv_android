package com.alisoltech.innovc.users

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.source.UserDataSource
import com.alisoltech.innovc.data.source.UserRepository
import com.alisoltech.innovc.features.users.UserContract
import com.alisoltech.innovc.features.users.UserPresenter
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue
import org.mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.capture
import org.mockito.kotlin.verify

class UserPresenterTest {

    private val USERS = listOf(User(0, "name1", "11-08-2019"), User(1, "name2", "11-08-2012"))

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var view: UserContract.View

    @InjectMocks
    private lateinit var userPresenter: UserPresenter

    @Captor
    private lateinit var userCallbackCaptor: ArgumentCaptor<UserDataSource.LoadUsersCallback>

    @Before
    fun setupUserPresenter() {
        MockitoAnnotations.openMocks(this)
        userPresenter.takeView(view)
    }

    @Test
    fun takeView_loadUsers() {
        verify(view).setLoadingIndicator(true)
        verify(userRepository).loadUsers(any())
    }

    @Test
    fun loadUsers_onUserLoaded() {
        verify(view).setLoadingIndicator(true)
        verify(userRepository).loadUsers(capture(userCallbackCaptor))
        userCallbackCaptor.value.onUsersLoaded(USERS)
        verify(view).showUsers(USERS)
        verify(view).setLoadingIndicator(false)
    }

    @Test
    fun loadUsers_onDataNotAvailable() {
        verify(view).setLoadingIndicator(true)
        verify(userRepository).loadUsers(capture(userCallbackCaptor))
        userCallbackCaptor.value.onDataNotAvailable()
        verify(view).showLoadingUserError()
        verify(view).setLoadingIndicator(false)
    }

    @Test
    fun addNewUser() {
        userPresenter.addNewUser()
        verify(view).showAddUser()
    }

    @Test
    fun dropView() {
        userPresenter.dropView()
        assertTrue(userPresenter.view == null)
    }

}