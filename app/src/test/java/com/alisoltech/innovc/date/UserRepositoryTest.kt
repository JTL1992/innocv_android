package com.alisoltech.innovc.date

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.source.UserDataSource
import com.alisoltech.innovc.data.source.UserRepository
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.capture
import org.mockito.kotlin.verify

class UserRepositoryTest {

    private val USERS = listOf(User(0, "name1", "11-08-2019"), User(1, "name2", "11-08-2012"))

    @Mock
    private lateinit var remoteUserDataSource: UserDataSource
    @Mock
    private lateinit var loadUsersCallback: UserDataSource.LoadUsersCallback
    @Captor
    private lateinit var loadUsersCaptor: ArgumentCaptor<UserDataSource.LoadUsersCallback>
    @InjectMocks
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var loadUserCallback: UserDataSource.LoadUserCallback
    @Mock
    private lateinit var loadDataCallback: UserDataSource.LoadDataCallback

    @Before
    fun setUpUserRepository() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun loadUsers_requestAllUsersFromRemoteSource() {
        userRepository.loadUsers(loadUsersCallback)
        verify(remoteUserDataSource).loadUsers(any())
    }

    @Test
    fun loadUsers_onLoadUsersFromRemoteSource() {
        userRepository.loadUsers(loadUsersCallback)
        verify(remoteUserDataSource).loadUsers(capture(loadUsersCaptor))
        loadUsersCaptor.value.onUsersLoaded(USERS)
        verify(loadUsersCallback).onUsersLoaded(USERS)
    }

    @Test
    fun loadUsers_onNotAvailableFromRemoteSource() {
        userRepository.loadUsers(loadUsersCallback)
        verify(remoteUserDataSource).loadUsers(capture(loadUsersCaptor))
        loadUsersCaptor.value.onDataNotAvailable()
        verify(loadUsersCallback).onDataNotAvailable()
    }

    @Test
    fun loadUser_requestOneUserFromRemoteSource() {

    }

    @Test
    fun saveUser_saveUserToRemoteSource() {

    }

    @Test
    fun remoteUser_removeOneUserFromRemoteSource() {

    }

    @Test
    fun editUser_editOneUserToRemoteSource() {

    }

}