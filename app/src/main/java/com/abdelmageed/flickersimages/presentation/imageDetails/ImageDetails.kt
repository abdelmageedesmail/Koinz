package com.abdelmageed.flickersimages.presentation.imageDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.abdelmageed.flickersimages.databinding.FragmentHomeBinding
import com.abdelmageed.flickersimages.databinding.FragmentImageDetailsBinding
import com.abdelmageed.flickersimages.extensions.applyImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetails : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding

    private val args: ImageDetailsArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            args.imageUrl?.let { ivImage.applyImage(it) }
        }
    }
}