package com.flywith24.flywith24_permissiondemo

import android.Manifest
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun click(view: View) {
        requestMultiplePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                allGranted = {
                    Snackbar.make(view, "all granted", Snackbar.LENGTH_SHORT).show()
                },
                denied = {
                    val result = it.toString().replace("android.permission.", "")
                    Snackbar.make(view, "未通过权限列表：\n$result", Snackbar.LENGTH_SHORT).show()
                })
    }
}