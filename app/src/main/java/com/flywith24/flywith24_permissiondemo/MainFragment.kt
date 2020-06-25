package com.flywith24.flywith24_permissiondemo

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author Flywith24
 * @date   2020/6/25
 * time   15:22
 * description
 */
class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestPermission.setOnClickListener {
            requestPermission(Manifest.permission.RECORD_AUDIO,
                granted = {
                    result.text = getString(R.string.result_granted)
                },
                denied = {
                    result.text = getString(R.string.result_denied)
                })
        }
    }
}