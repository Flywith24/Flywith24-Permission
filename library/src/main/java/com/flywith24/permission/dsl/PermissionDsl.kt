package com.flywith24.permission.dsl

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.flywith24.permission.requestMultiplePermissions
import com.flywith24.permission.requestPermission

/**
 * @author Flywith24
 * @date   2020/6/28
 * time   14:05
 * description
 */

inline fun Fragment.permission(
    permission: String,
    builderPermission: PermissionBuilder.() -> Unit
) {
    val builder = PermissionBuilder()
    builder.builderPermission()
    requestPermission(
        permission,
        granted = builder.granted,
        denied = builder.denied,
        explained = builder.explained
    )
}

inline fun Fragment.permissions(
    vararg permissions: String,
    builderPermission: MultiPermissionBuilder.() -> Unit
) {
    val builder = MultiPermissionBuilder()
    builder.builderPermission()
    requestMultiplePermissions(
        *permissions,
        allGranted = builder.allGranted,
        denied = builder.denied,
        explained = builder.explained
    )
}

inline fun ComponentActivity.permission(
    permission: String,
    builderPermission: PermissionBuilder.() -> Unit
) {
    val builder = PermissionBuilder()
    builder.builderPermission()
    requestPermission(
        permission,
        granted = builder.granted,
        denied = builder.denied,
        explained = builder.explained
    )
}

inline fun ComponentActivity.permissions(
    vararg permissions: String,
    builderPermission: MultiPermissionBuilder.() -> Unit
) {
    val builder = MultiPermissionBuilder()
    builder.builderPermission()
    requestMultiplePermissions(
        *permissions,
        allGranted = builder.allGranted,
        denied = builder.denied,
        explained = builder.explained
    )
}