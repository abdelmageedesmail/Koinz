package com.abdelmageed.flickersimages.presentation.homeImages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelmageed.flickersimages.data.common.utils.WrappedResponseWithError
import com.abdelmageed.flickersimages.data.module.remote.dto.BaseErrorResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import com.abdelmageed.flickersimages.domain.base.BaseResult
import com.abdelmageed.flickersimages.domain.images.ImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class HomeFragmentState {
    object Init : HomeFragmentState()

    @androidx.annotation.Keep
    data class IsLoading(val isLoading: Boolean) : HomeFragmentState()

    @androidx.annotation.Keep
    data class ShowToast(val message: String) : HomeFragmentState()

    @androidx.annotation.Keep
    data class GetImages(val flickerImagesResponse: FlickerImagesResponse) : HomeFragmentState()

    @androidx.annotation.Keep
    data class ErrorGetImages(val errorResponse: WrappedResponseWithError<Unit, BaseErrorResponse>) :
        HomeFragmentState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val imagesUseCase: ImagesUseCase) : ViewModel() {

    private val imageState = MutableStateFlow<HomeFragmentState>(HomeFragmentState.Init)
    val mImageState: StateFlow<HomeFragmentState> get() = imageState


    private fun setLoading() {
        imageState.value = HomeFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        imageState.value = HomeFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        imageState.value = HomeFragmentState.ShowToast(message)
    }

    private fun successGetImage(flickerImagesResponse: FlickerImagesResponse) {
        imageState.value = HomeFragmentState.GetImages(flickerImagesResponse)
    }

    private fun failedGetImage(rawResponse: WrappedResponseWithError<Unit, BaseErrorResponse>) {
        imageState.value = HomeFragmentState.ErrorGetImages(rawResponse)
    }

    fun getImages(page: Int, perPage: Int) {
        viewModelScope.launch {
            imagesUseCase.invoke(page = page, perPage = perPage).onStart {
                setLoading()
            }.catch { exception ->
                exception.message?.let { showToast(it) }
                hideLoading()
            }.collect { result ->
                hideLoading()
                when (result) {
                    is BaseResult.Success -> result.data?.let { successGetImage(it) }
                    is BaseResult.Error -> failedGetImage(result.rawResponse)
                }
            }
        }
    }

}