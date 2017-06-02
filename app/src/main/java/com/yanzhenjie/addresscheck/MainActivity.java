/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.addresscheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.addresscheck.address.AddressCheckActivity;
import com.yanzhenjie.addresscheck.address.City;

import java.util.ArrayList;

/**
 * <p>这里可以是任何页面，主要用来调用地址选择组件。</p>
 * Created by YanZhenjie on 2017/6/1.
 */
public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView) findViewById(R.id.tv_message);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddress();
            }
        });
    }

    /**
     * 去选择地址。
     */
    private void selectAddress() {
        Intent intent = new Intent(this, AddressCheckActivity.class);
        startActivityForResult(intent, 666);
    }

    /**
     * 解析地址。
     */
    private void parseAddress(Intent intent) {
        ArrayList<City> cityList = AddressCheckActivity.parse(intent);

        String tvAddress = "", lastId = "";
        if (cityList != null) {
            for (int i = 0; i < cityList.size(); i++) {
                City city = cityList.get(i);
                lastId = city.getId();
                tvAddress += city.getName();
            }
        }
        mTextView.setText(tvAddress + "\n提交到服务器的id是：" + lastId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case 666: {
                parseAddress(data);
                break;
            }
        }
    }
}
