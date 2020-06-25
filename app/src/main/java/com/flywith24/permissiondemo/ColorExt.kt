package com.flywith24.permissiondemo

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use

/**
 * @author Flywith24
 * @date   2020/6/25
 * time   19:22
 * description
 * 颜色扩展
 */

/**
 * 根据主题属性获取颜色
 */
@ColorInt
fun Context.getThemeColor(@AttrRes themeAttrId: Int): Int {
    return obtainStyledAttributes(intArrayOf(themeAttrId)).use {
        it.getColor(0, Color.MAGENTA)
    }
}