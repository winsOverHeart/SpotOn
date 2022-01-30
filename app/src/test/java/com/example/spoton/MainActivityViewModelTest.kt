package com.example.spoton

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.spoton.data.model.Data
import com.example.spoton.domain.usecase.AddMoreDataUseCase
import com.example.spoton.domain.usecase.GetCryptoDataUseCase
import com.example.spoton.domain.usecase.UpdateCryptoUsecase
import com.example.spoton.presentation.viemodel.MainActivityViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        val fakeCryptoRepository = FakeCryptoRepository()
        val getCryptoDataUseCase = GetCryptoDataUseCase(fakeCryptoRepository)
        val updateCryptoUseCase = UpdateCryptoUsecase(fakeCryptoRepository)
        val addCryptoDataUseCase = AddMoreDataUseCase(fakeCryptoRepository)
        viewModel = MainActivityViewModel(getCryptoDataUseCase, updateCryptoUseCase, addCryptoDataUseCase)
    }

    @Test
    fun getData_returnsCurrentList() {
        val datas = mutableListOf<Data>()
        datas.add(
            Data(
                "1.0", "overview1", "path1", "date1", "title1",
                "", "", "",
                "", "", "", ""
            )
        )
        datas.add(
            Data(
                "2.0", "overview2", "path2", "date2", "title2", "", "", "",
                "", "", "", ""
            )
        )
        val currentList = viewModel.getCryptoData().getOrAwaitValue()
        assertThat(currentList).isEqualTo(datas)
    }

    @Test
    fun updateCrypto_returnsUpdatedList() {
        val dataList = mutableListOf<Data>()
        dataList.add(
            Data(
                "3.0", "overview3", "path3", "date3", "title3", "", "", "",
                "", "", "", ""
            )
        )
        dataList.add(
            Data(
                "4.0", "overview4", "path4", "date4", "title4", "", "", "",
                "", "", "", ""
            )
        )
        val updatedList = viewModel.updateCryptoData().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(updatedList)
    }
}
