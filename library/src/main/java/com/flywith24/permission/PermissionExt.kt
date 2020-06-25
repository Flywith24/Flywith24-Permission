package com.flywith24.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment


/**
 * @author Flywith24
 * @date   2020/6/25
 * time   15:30
 * description
 * 权限请求扩展函数
 */

const val DENIED = "DENIED"
const val EXPLAINED = "EXPLAINED"

/**
 * [permission] 权限名称
 * [granted] 申请成功
 * [denied] 被拒绝且未勾选不再询问
 * [explained] 被拒绝且勾选不再询问
 */
inline fun ComponentActivity.requestPermission(
    permission: String,
    crossinline granted: (permission: String) -> Unit = {},
    crossinline denied: (permission: String) -> Unit = {},
    crossinline explained: (permission: String) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        when {
            result -> granted.invoke(permission)
            shouldShowRequestPermissionRationale(permission) -> denied.invoke(permission)
            else -> explained.invoke(permission)
        }
    }.launch(permission)
}

/**
 * [permissions] 权限数组
 * [allGranted] 所有权限均申请成功
 * [denied] 被拒绝且未勾选不再询问，同时被拒绝且未勾选不再询问的权限列表
 * [explained] 被拒绝且勾选不再询问，同时被拒绝且勾选不再询问的权限列表
 */
inline fun ComponentActivity.requestMultiplePermissions(
    vararg permissions: String,
    crossinline allGranted: () -> Unit = {},
    crossinline denied: (List<String>) -> Unit = {},
    crossinline explained: (List<String>) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
        //过滤 value 为 false 的元素并转换为 list
        val deniedList = result.filter { !it.value }.map { it.key }
        when {
            deniedList.isNotEmpty() -> {
                //对被拒绝全选列表进行分组，分组条件为是否勾选不再询问
                val map = deniedList.groupBy { permission ->
                    if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
                }
                //被拒接且没勾选不再询问
                map[DENIED]?.let { denied.invoke(it) }
                //被拒接且勾选不再询问
                map[EXPLAINED]?.let { explained.invoke(it) }
            }
            else -> allGranted.invoke()
        }
    }.launch(permissions)
}


/**
 * [permission] 权限名称
 * [granted] 申请成功
 * [denied] 被拒绝且未勾选不再询问
 * [explained] 被拒绝且勾选不再询问
 */
inline fun Fragment.requestPermission(
    permission: String,
    crossinline granted: (permission: String) -> Unit = {},
    crossinline denied: (permission: String) -> Unit = {},
    crossinline explained: (permission: String) -> Unit = {}

) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        when {
            result -> granted.invoke(permission)
            shouldShowRequestPermissionRationale(permission) -> denied.invoke(permission)
            else -> explained.invoke(permission)
        }
    }.launch(permission)
}

/**
 * [permissions] 权限数组
 * [allGranted] 所有权限均申请成功
 * [denied] 被拒绝且未勾选不再询问，同时被拒绝且未勾选不再询问的权限列表
 * [explained] 被拒绝且勾选不再询问，同时被拒绝且勾选不再询问的权限列表
 */
inline fun Fragment.requestMultiplePermissions(
    vararg permissions: String,
    crossinline allGranted: () -> Unit = {},
    crossinline denied: (List<String>) -> Unit = {},
    crossinline explained: (List<String>) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
        //过滤 value 为 false 的元素并转换为 list
        val deniedList = result.filter { !it.value }.map { it.key }
        when {
            deniedList.isNotEmpty() -> {
                //对被拒绝全选列表进行分组，分组条件为是否勾选不再询问
                val map = deniedList.groupBy { permission ->
                    if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
                }
                //被拒接且没勾选不再询问
                map[DENIED]?.let { denied.invoke(it) }
                //被拒接且勾选不再询问
                map[EXPLAINED]?.let { explained.invoke(it) }
            }
            else -> allGranted.invoke()
        }
    }.launch(permissions)
}

