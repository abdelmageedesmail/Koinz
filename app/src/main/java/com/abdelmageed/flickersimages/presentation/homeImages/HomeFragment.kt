package com.abdelmageed.flickersimages.presentation.homeImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.abdelmageed.flickersimages.data.common.utils.WrappedResponseWithError
import com.abdelmageed.flickersimages.data.module.remote.dto.BaseErrorResponse
import com.abdelmageed.flickersimages.data.module.remote.dto.FlickerImagesResponse
import com.abdelmageed.flickersimages.databinding.FragmentHomeBinding
import com.abdelmageed.flickersimages.extensions.initProgressDialog
import com.abdelmageed.flickersimages.extensions.showToast
import com.abdelmageed.flickersimages.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private val dialog by lazy { initProgressDialog(requireActivity(), true) }
    private var adapter: ImagesAdapter? = null
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImages(page, 20)
        observe()
        binding.apply {

            page = 1
            adapter = ImagesAdapter(mutableListOf()) { imageUrl ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToImageDetails(
                        imageUrl
                    )
                )
            }

            rvImages.adapter = adapter
            rvImages.setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
            rvImages.layoutManager = gridLayoutManager


            rvImages.addOnScrollListener(
                object :
                    PaginationScrollListener(rvImages.layoutManager as GridLayoutManager) {
                    override fun isLastPage(): Boolean {
                        return isLastPage
                    }

                    override fun isLoading(): Boolean {
                        return isLoading
                    }

                    override fun loadMoreItems() {
                        isLoading = true

                        getMoreItems()
                    }
                })
        }

    }

    private fun handleStateChange(state: HomeFragmentState) {
        when (state) {
            is HomeFragmentState.ShowToast -> requireContext().showToast(state.message)
            is HomeFragmentState.IsLoading -> handleLoading(state.isLoading)
            is HomeFragmentState.GetImages -> handleSuccessGetImage(state.flickerImagesResponse)
            is HomeFragmentState.ErrorGetImages -> handleErrorGetImage(state.errorResponse)
            is HomeFragmentState.Init -> Unit
        }
    }

    private fun handleErrorGetImage(errorResponse: WrappedResponseWithError<Unit, BaseErrorResponse>) {
        errorResponse.message?.let { requireContext().showToast(it) }
    }

    private fun handleSuccessGetImage(flickerImagesResponse: FlickerImagesResponse) {
        if ((flickerImagesResponse.photos?.pages!! > page)) {
            isLastPage = false
            page++
            adapter?.addItems(flickerImagesResponse.photos.photo)
        } else {
            isLastPage = true
        }
    }

    fun getMoreItems() {
        viewModel.getImages(page, 20)
        isLoading = false
    }

    private fun observe() {
        viewModel.mImageState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) dialog.show()
        else dialog.dismiss()
    }


}