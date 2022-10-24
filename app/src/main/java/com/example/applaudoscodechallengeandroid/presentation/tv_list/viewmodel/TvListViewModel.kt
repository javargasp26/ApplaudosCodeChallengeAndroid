package com.example.applaudoscodechallengeandroid.presentation.tv_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudoscodechallengeandroid.domain.GetTvListUSeCase
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvListViewModel@Inject constructor(private val tvListUSeCase: GetTvListUSeCase): ViewModel() {

    var tvListResponse:List<TvInfo> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getTvList() {
        viewModelScope.launch {
            val result = tvListUSeCase()
            try {
                tvListResponse = result
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}