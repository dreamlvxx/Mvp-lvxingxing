package com.sbkj.shunbaowallet.mvp_lvxingxing.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sbkj.shunbaowallet.mvp_lvxingxing.R;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public class Person implements MultiItemEntity, Parcelable  {
    private String name;
    private String sex;
    private String id;

    private int imageResId = R.mipmap.ic_launcher;

    public int itemType =1;
    public static final int CUSTOM_TYPE =1;
    public static final int IMAGE_TYPE =2;


    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sex);
        dest.writeString(this.id);
        dest.writeInt(this.imageResId);
        dest.writeInt(this.itemType);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.sex = in.readString();
        this.id = in.readString();
        this.imageResId = in.readInt();
        this.itemType = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
