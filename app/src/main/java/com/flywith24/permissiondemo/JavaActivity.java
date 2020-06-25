package com.flywith24.permissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

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
public class JavaActivity extends AppCompatActivity {
    private static final String TAG = "JavaActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        PermissionUtils.requestMultiplePermissions(this, new MultiPermissionResultListener() {
            @Override
            public void allGranted() {
                Log.i(TAG, "allGranted: ");
            }

            @Override
            public void denied(List<String> list) {
                Log.i(TAG, "denied: " + list.toString());
            }

            @Override
            public void explained(List<String> list) {
                Log.i(TAG, "explained: " + list.toString());
            }
        }, permissions);

        PermissionUtils.requestPermission(this, Manifest.permission.RECORD_AUDIO, new PermissionResultListener() {
            @Override
            public void granted(String permission) {
                Log.i(TAG, "granted: ");
            }

            @Override
            public void denied(String permission) {
                Log.i(TAG, "denied: ");
            }

            @Override
            public void explained(String permission) {
                Log.i(TAG, "explained: ");
            }
        });
    }
}