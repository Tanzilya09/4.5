package com.example.a45

import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.a45.fragments.FirstFragment


fun ImageView.setImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .circleCrop()
        .into(this)
}

fun FirstFragment.hasPermissionCheckAndRequest(
    requestPermissionLauncher: ActivityResultLauncher<Array<String>>,
    permission: Array<String>,
): Boolean {
    for (per in permission) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                per
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(permission)
            return false
        }
    }
    return true
}