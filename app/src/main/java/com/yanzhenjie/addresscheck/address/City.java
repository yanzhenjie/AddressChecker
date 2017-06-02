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
package com.yanzhenjie.addresscheck.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <p>区域实体。</p>
 * Created by YanZhenjie on 2017/6/1.
 */
public class City implements Parcelable {

    /**
     * id。
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 名称。
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 子项。
     */
    @JSONField(name = "children")
    private List<City> mCityList;

    /**
     * 是否选中。
     */
    private boolean isSelect;

    public City() {
    }

    protected City(Parcel in) {
        id = in.readString();
        name = in.readString();
        mCityList = in.createTypedArrayList(City.CREATOR);
        isSelect = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeTypedList(mCityList);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public List<City> getCityList() {
        return mCityList;
    }

    public void setCityList(List<City> mCityList) {
        this.mCityList = mCityList;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean mSelect) {
        isSelect = mSelect;
    }

    public City(String mId, String mName, List<City> mCityList, boolean mIsSelect) {

        id = mId;
        name = mName;
        this.mCityList = mCityList;
        isSelect = mIsSelect;
    }

}
