package com.project.api.presentation.screens.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.api.domain.Profile.GetSessionIdUseCase
import com.project.api.domain.Profile.GetUserAccountUseCase
import com.project.api.domain.Profile.GetUserTokenUseCase
import com.project.api.model.UIState
import com.project.api.model.UserAccount
import com.project.api.model.UserTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
    private val getUserAccountUseCase: GetUserAccountUseCase

): ViewModel(){
    var userTokenState: MutableStateFlow<UIState<UserTokenResponse>> =
        MutableStateFlow(UIState.Loading())

    var userSessionState: MutableStateFlow<UIState<UserTokenResponse>> =
        MutableStateFlow(UIState.Loading())

    var userAccountState: MutableStateFlow<UIState<UserAccount>> =
        MutableStateFlow(UIState.Loading())

    fun getUserToken(){
        viewModelScope.launch {
            when(val response = getUserTokenUseCase.invoke()){
                is UIState.Empty -> userTokenState.value = UIState.Empty(title = response.title)
                is UIState.Error -> userTokenState.value = UIState.Error(response.error)
                is UIState.Loading -> userTokenState.value = UIState.Loading()
                is UIState.Success -> userTokenState.value = UIState.Success(response.data)
            }
        }
    }

    fun getSessionId(requestToken: String){
        viewModelScope.launch {
            when(val response = getSessionIdUseCase.invoke(requestToken)){
                is UIState.Empty -> userSessionState.value = UIState.Empty(title = response.title)
                is UIState.Error -> userSessionState.value = UIState.Error(response.error)
                is UIState.Loading -> userSessionState.value = UIState.Loading()
                is UIState.Success -> userSessionState.value = UIState.Success(response.data)
            }
        }
    }

    fun getUserAccount(sessionId:String){
        viewModelScope.launch {
            when(val response = getUserAccountUseCase.invoke(sessionId)){
                is UIState.Empty -> userAccountState.value = UIState.Empty(title = response.title)
                is UIState.Error -> userAccountState.value = UIState.Error(response.error)
                is UIState.Loading -> userAccountState.value = UIState.Loading()
                is UIState.Success -> userAccountState.value = UIState.Success(response.data)
            }
        }
    }

}