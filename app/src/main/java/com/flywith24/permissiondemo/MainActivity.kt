package com.flywith24.permissiondemo

import android.Manifest
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.permission.dsl.permissions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Flywith24
 * @date   2020/6/25
 * time   15:22
 * description
 * Kotlin Demo
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun click(view: View) {
        deniedResult.text = ""
        explainedResult.text = ""

        // DSL 模式
        permissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ) {
            allGranted = {
                Snackbar.make(view, getString(R.string.result_granted_list), Snackbar.LENGTH_SHORT)
                    .show()
            }
            denied = {
                //防止太长体验不好，真实使用时无需做此操作
                val result = it.toString().replace("android.permission.", "")
                with(deniedResult) {
                    text = getString(R.string.result_denied_list, result)
                    setTextColor(context.getThemeColor(R.attr.errorTextColor))
                }
            }
            explained = {
                //防止太长体验不好，真实使用时无需做此操作
                val result = it.toString().replace("android.permission.", "")
                with(explainedResult) {
                    text = getString(R.string.result_explained_list, result)
                    setTextColor(context.getThemeColor(R.attr.errorTextColor))
                }
            }
        }

        // 普通扩展函数模式
        /*requestMultiplePermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            allGranted = {
                Snackbar.make(view, getString(R.string.result_granted_list), Snackbar.LENGTH_SHORT)
                    .show()
            },
            denied = {
                //防止太长体验不好，真实使用时无需做此操作
                val result = it.toString().replace("android.permission.", "")
                with(deniedResult) {
                    text = getString(R.string.result_denied_list, result)
                    setTextColor(context.getThemeColor(R.attr.errorTextColor))
                }
            },
            explained = {
                //防止太长体验不好，真实使用时无需做此操作
                val result = it.toString().replace("android.permission.", "")
                with(explainedResult) {
                    text = getString(R.string.result_explained_list, result)
                    setTextColor(context.getThemeColor(R.attr.errorTextColor))
                }
            })*/
    }
}