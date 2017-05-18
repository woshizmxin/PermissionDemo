/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.marsthink.permissiondemo.contacts.ContactActivity;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionGen.with(MainActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.RECEIVE_SMS,
                                Manifest.permission.WRITE_CONTACTS)
                        .request();
            }
        });

        findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PermissionGen.needPermission(MainActivity.this, 200, Manifest.permission.CAMERA);
                PermissionGen.with(MainActivity.this).addRequestCode(200).permissions(Manifest.permission.CAMERA).request();
            }
        });
    }

    @PermissionSuccess(requestCode = 100)
    public void test() {
        startActivity(new Intent(this, ContactActivity.class));
    }

    @PermissionFail(requestCode = 100)
    private void test2() {
        Dlog.debug("contact fail");
    }

    @PermissionSuccess(requestCode = 200)
    public void openCamera() {
        Dlog.debug("open camera success");
    }

    @PermissionFail(requestCode = 200)
    public void failOpenCamera() {
        Toast.makeText(this, "Camera permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
