package com.flywith24.permission.java;

import java.util.List;

/**
 * @author Flywith24
 * @date 2020/6/25
 * time   20:44
 * description
 */
public interface MultiPermissionResultListener {
    void allGranted();

    void denied(List<String> list);

    void explained(List<String> list);
}
