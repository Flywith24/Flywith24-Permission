package com.flywith24.permission.java;

/**
 * @author Flywith24
 * @date 2020/6/25
 * time   20:47
 * description
 */
public interface PermissionResultListener {
    void granted(String permission);

    void denied(String permission);

    void explained(String permission);
}
