package com.example.spoton.presentation.viemodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spoton.domain.usecase.AddMoreDataUseCase
import com.example.spoton.domain.usecase.GetCryptoDataUseCase
import com.example.spoton.domain.usecase.UpdateCryptoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getCryptoDataUseCase: GetCryptoDataUseCase, private val updateCryptoUsecase: UpdateCryptoUsecase, private val addMoreDataUseCase: AddMoreDataUseCase) :
    ViewModel(), LifecycleObserver {
    private var initialOffSet: Int = 20
    private var limit: Int = 20

    fun getCryptoData() = liveData {
        val dataList = getCryptoDataUseCase.execute()
        emit(dataList)
    }

    fun updateCryptoData() = liveData {
        val dataList = updateCryptoUsecase.execute()
        emit(dataList)
        initialOffSet = 0
    }

    fun addMoreData() = liveData {
        val dataList = addMoreDataUseCase.execute(limit, initialOffSet)
        emit(dataList)
        if (dataList != null && dataList.size> 0) {
            initialOffSet += 20
        }
    }
}
