package com.flywith24.permissiondemo

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.flywith24.permission.requestPermission
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
                granted = { permission ->
                    //防止太长体验不好，真实使用时无需做此操作
                    val result = permission.replace("android.permission.", "")
                    with(tvResult) {
                        text = getString(R.string.result_granted, result)
                        setTextColor(context.getThemeColor(R.attr.colorSurface))
                    }
                },
                denied = { permission ->
                    //防止太长体验不好，真实使用时无需做此操作
                    val result = permission.replace("android.permission.", "")
                    with(tvResult) {
                        text = getString(R.string.result_denied, result)
                        setTextColor(context.getThemeColor(R.attr.errorTextColor))
                    }
                },
                explained = { permission ->
                    //防止太长体验不好，真实使用时无需做此操作
                    val result = permission.replace("android.permission.", "")
                    with(tvResult) {
                        text = getString(R.string.result_explained, result)
                        setTextColor(context.getThemeColor(R.attr.errorTextColor))
                    }
                })
        }
    }
}