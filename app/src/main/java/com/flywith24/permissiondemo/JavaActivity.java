package com.flywith24.permissiondemo;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.flywith24.permission.java.MultiPermissionResultListener;
import com.flywith24.permission.java.PermissionResultListener;
import com.flywith24.permission.java.PermissionUtils;

import java.util.List;


/**
 * @author Flywith24
 * @date 2020/6/25
 * time   15:22
 * description
 * Java Demo
 */
class JavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        PermissionUtils.requestMultiplePermissions(this, new MultiPermissionResultListener() {
            @Override
            public void allGranted() {

            }

            @Override
            public void denied(List<String> list) {

            }

            @Override
            public void explained(List<String> list) {

            }
        }, permissions);

        PermissionUtils.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionResultListener() {
            @Override
            public void granted(String permission) {

            }

            @Override
            public void denied(String permission) {

            }

            @Override
            public void explained(String permission) {

            }
        });
    }
}