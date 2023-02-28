package com.example.a45.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.a45.R
import com.example.a45.databinding.FragmentFirstBinding
import com.example.a45.hasPermissionCheckAndRequest
import com.example.a45.setImage


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPermission()
    }

    private var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        for (permission in isGranted) {
            when {
                permission.value -> fileChooserContract.launch("image/*")
                !shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    findNavController().navigate(R.id.secondFragment)
                }

            }
        }
    }

    private fun setupPermission() {
        binding.btn.setOnClickListener {
            if (hasPermissionCheckAndRequest(
                    requestPermissionLauncher,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            ) {
                fileChooserContract.launch("image/*")
            }
        }
    }

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageuri ->
            if (imageuri != null) {
                binding.image.setImage(imageuri.toString())
                uri = imageuri
            }
        }
}