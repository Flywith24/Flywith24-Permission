package com.flywith24.permission.dsl

/**
 * @author Flywith24
 * @date   2020/6/28
 * time   14:08
 * description
 */

class PermissionBuilder {
    var granted: (permission: String) -> Unit = {}
    var denied: (permission: String) -> Unit = {}
    var explained: (permission: String) -> Unit = {}
}