package com.flywith24.flywith24_permissiondemo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    fun click(view: View) {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), {
            Snackbar.make(view, "all granted", Snackbar.LENGTH_SHORT).show()
        }, {
            Log.i("yyz11", "list $it")

        })

       /* requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, {
            Log.i("yyz11", "granted")
        })*/
    }
}