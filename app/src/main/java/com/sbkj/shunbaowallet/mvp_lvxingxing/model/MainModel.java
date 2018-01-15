package com.sbkj.shunbaowallet.mvp_lvxingxing.model;

import android.util.Log;

import com.sbkj.shunbaowallet.mvp_lvxingxing.bean.Person;
import com.sbkj.shunbaowallet.mvp_lvxingxing.contract.MainContract;

import java.util.ArrayList;

/**
 * Created by lvxingxing on 2017/12/12.
 *
 * @author lvxingxing
 */

public class MainModel implements MainContract.IMainModel {

    private final String TAG="MainModel";

    @Override
    public void modelMethod1() {
        //do something
        Log.i(TAG, "modelMethod1: zhixingle--=-=-=");
    }

    @Override
    public ArrayList<Person> requestData() {
        ArrayList<Person> list =new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person =new Person();
            person.setId(i+"");
            person.setName("xx"+i);
            person.setSex("boy");
            if (i%5==0){
                person.itemType = person.IMAGE_TYPE;
            }
            list.add(person);
        }
        return list;
    }


}
