package com.flywith24.permission.java;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.flywith24.permission.PermissionExtKt;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/**
 * @author Flywith24
 * @date 2020/6/25
 * time   20:50
 * description
 * java 权限请求工具类
 */
public class PermissionUtils {
    /**
     * 请求单一权限
     *
     * @param activity   activity
     * @param permission 权限名
     * @param listener   回调
     */
    public static void requestPermission(@NonNull ComponentActivity activity,
                                         @NonNull String permission,
                                         @NonNull final PermissionResultListener listener) {
        PermissionExtKt.requestPermission(activity, permission,
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.granted(permission);
                        return null;
                    }
                }, new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.denied(permission);
                        return null;
                    }
                }, new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.explained(permission);
                        return null;
                    }
                });
    }

    /**
     * 请求单一权限
     *
     * @param fragment   fragment
     * @param permission 权限名
     * @param listener   回调
     */
    public static void requestPermission(@NonNull Fragment fragment,
                                         @NonNull String permission,
                                         @NonNull final PermissionResultListener listener) {
        PermissionExtKt.requestPermission(fragment, permission,
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.granted(permission);
                        return null;
                    }
                }, new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.denied(permission);
                        return null;
                    }
                }, new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String permission) {
                        listener.explained(permission);
                        return null;
                    }
                });
    }

    /**
     * 请求多个权限
     *
     * @param fragment    fragment
     * @param listener    回调
     * @param permissions 权限数组
     */
    public static void requestMultiplePermissions(@NonNull Fragment fragment,
                                                  @NonNull final MultiPermissionResultListener listener,
                                                  @NonNull String... permissions) {
        PermissionExtKt.requestMultiplePermissions(fragment, permissions, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                listener.allGranted();
                return null;
            }
        }, new Function1<List<String>, Unit>() {
            @Override
            public Unit invoke(List<String> list) {
                listener.denied(list);
                return null;
            }
        }, new Function1<List<String>, Unit>() {
            @Override
            public Unit invoke(List<String> list) {
                listener.explained(list);
                return null;
            }
        });
    }


    /**
     * 请求多个权限
     *
     * @param activity    activity
     * @param listener    回调
     * @param permissions 权限数组
     */
    public static void requestMultiplePermissions(@NonNull ComponentActivity activity,
                                                  @NonNull final MultiPermissionResultListener listener,
                                                  @NonNull String... permissions) {
        PermissionExtKt.requestMultiplePermissions(activity, permissions, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                listener.allGranted();
                return null;
            }
        }, new Function1<List<String>, Unit>() {
            @Override
            public Unit invoke(List<String> list) {
                listener.denied(list);
                return null;
            }
        }, new Function1<List<String>, Unit>() {
            @Override
            public Unit invoke(List<String> list) {
                listener.explained(list);
                return null;
            }
        });
    }
}
