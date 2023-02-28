package com.example.a45.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.a45.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SecondFragment : BottomSheetDialogFragment(){

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
    }

    private fun setList() {
        binding.btn1.setOnClickListener {
            dismiss()
        }
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        binding.btn2.setOnClickListener {
            resultLauncher.launch(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                    Uri.parse("package:${requireActivity().packageName}")
                )
            )
            dismiss()
        }
    }
}