package com.goms.presentation.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.ViewModel
import com.goms.domain.data.outing.OutingCountResponseData
import com.goms.domain.data.profile.response.ProfileResponseData
import com.goms.domain.usecase.outing.OutingCountUseCase
import com.goms.domain.usecase.outing.OutingListUseCase
import com.goms.domain.usecase.outing.OutingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class OutingViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val outingUseCase: OutingUseCase,
    private val outingListUseCase: OutingListUseCase,
    private val outingCountUseCase: OutingCountUseCase
): ViewModel() {
    private val _isOuting: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isOuting: StateFlow<Boolean?> = _isOuting

    private val _outingList: MutableStateFlow<List<ProfileResponseData>?> = MutableStateFlow(null)
    val outingList: StateFlow<List<ProfileResponseData>?> = _outingList

    private val _outingCount: MutableStateFlow<OutingCountResponseData?> = MutableStateFlow(null)
    val outingCount: StateFlow<OutingCountResponseData?> = _outingCount

    suspend fun outingLogic() {
        outingUseCase().onFailure {
            _isOuting.value = false
            if (it is HttpException) {
                when(it.code()) {
                    400 -> _isOuting.value = null
                    401 -> Log.d("TAG", "outingLogic: 유효하지 않은 토큰")
                    500 -> Log.d("TAG", "outingLogic: server error")
                }
            } else Log.d("TAG", "outingLogic error: $it")
        }.onSuccess {
            _isOuting.value = true
        }
    }

    suspend fun outingListLogic() {
        outingListUseCase().catch {
            if (it is HttpException) {
                when(it.code()) {
                    401 -> Log.d("TAG", "outingListLogic: 토큰 에러")
                    404 -> _outingList.value = emptyList()
                    500 -> Log.d("TAG", "outingListLogic: 서버 에러")
                }
            } else Log.d("TAG", "outingListLogic: $it")
        }.collect {
            manageOutingSharedPreference()
            _outingList.value = it
        }
    }

    suspend fun outingCount() {
        outingCountUseCase().catch {
            Log.d("TAG", "outingCount: $it")
        }.collect {
            _outingCount.value = it
        }
    }

    // 현재 외출 중인지 상태를 체크한다.
    private val sharedPreferences = context.getSharedPreferences("userOuting", MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private fun manageOutingSharedPreference() {
        if (!sharedPreferences.getBoolean("userOuting", false))
            editor.putBoolean("userOuting", true)
        else editor.putBoolean("userOuting", false)
    }
}