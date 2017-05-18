/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.permissiondemo.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marsthink.permissiondemo.R;


public class ContactActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, ContactFragment.newInstance())
        .commitAllowingStateLoss();
  }
}
