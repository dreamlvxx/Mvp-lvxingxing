package com.sbkj.shunbaowallet.mvp_lvxingxing;

import android.app.Application;

import java.util.Observable;

/**
 * Created by lvxingxing on 2018/1/30.
 *
 * @author lvxingxing
 */

public class ApplicationManager extends Observable {
    public static BaseApplication app;
    public static void init(Application context) {
        app = (BaseApplication) context;
    }
}
