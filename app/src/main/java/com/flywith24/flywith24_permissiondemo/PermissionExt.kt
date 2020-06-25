package com.flywith24.flywith24_permissiondemo

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts


/**
 * @author Flywith24
 * @date   2020/6/25
 * time   15:30
 * description
 */
inline fun ComponentActivity.requestPermission(
    permission: String,
    crossinline granted: () -> Unit = {},
    crossinline denied: () -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (result) granted.invoke()
        else denied.invoke()
    }.launch(permission)
}

inline fun ComponentActivity.requestPermissions(
    permissions: Array<String>,
    crossinline allGranted: () -> Unit = {},
    crossinline denied: (List<String>) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
        //过滤 value 为 false 的元素并转换为 list
        val deniedList = result.filter { !it.value }.map { it.key }

        if (deniedList.isNotEmpty()) denied.invoke(deniedList) else allGranted.invoke()
    }.launch(permissions)
}