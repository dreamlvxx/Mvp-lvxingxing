package com.sbkj.shunbaowallet.mvp_lvxingxing.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sbkj.shunbaowallet.mvp_lvxingxing.R;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public class Person implements MultiItemEntity {
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
}
