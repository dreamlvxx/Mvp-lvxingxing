package com.sbkj.shunbaowallet.mvp_lvxingxing.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbkj.shunbaowallet.mvp_lvxingxing.R;


/**
 *
 * @author lvxingxing
 * @date 2017/11/23
 *
 * @auther lvxingxing
 */

public class CurrentDaySummaryView extends LinearLayout {
    private View v;

    private Drawable leftImage;
    private String leftText;
    private String dayMoney;
    private String dayFee;

    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tvRight;

    private LayoutInflater layoutInflater;

    public CurrentDaySummaryView(Context context) {
        this(context,null);
    }

    public CurrentDaySummaryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CurrentDaySummaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =context.obtainStyledAttributes(attrs, R.styleable.CurrentDaySummaryView);
        leftImage =typedArray.getDrawable(R.styleable.CurrentDaySummaryView_daySummaryleftImage);
        leftText =typedArray.getString(R.styleable.CurrentDaySummaryView_daySummaryleftText);
        dayMoney =typedArray.getString(R.styleable.CurrentDaySummaryView_daySummarydayMoney);
        dayFee =typedArray.getString(R.styleable.CurrentDaySummaryView_daySummarydayFee);
        typedArray.recycle();

        layoutInflater =LayoutInflater.from(context);
        initLayout();
    }


    private void initLayout(){
        v =layoutInflater.inflate(R.layout.current_day_summary,this);
        ivLeft = (ImageView) v.findViewById(R.id.imageLeft);
        tvLeft = (TextView) v.findViewById(R.id.tv_left);
        tvCenter = (TextView) v.findViewById(R.id.tv_center);
        tvRight = (TextView) v.findViewById(R.id.tv_right);
        setAttr();
    }

    private void setAttr(){
        ivLeft.setImageDrawable(leftImage);
        tvLeft.setText(leftText);
        tvCenter.setText(dayMoney);
        tvRight.setText(dayFee);
    }

    public View getrootView(){
        return v;
    }

    public void setLeftImage(@DrawableRes int resId){
        leftImage =getResources().getDrawable(resId);
        ivLeft.setImageResource(resId);
    }

    public void setLeftImage(Drawable d){
        leftImage =d;
        ivLeft.setImageDrawable(d);
    }

    public void setLeftText(@StringRes int resId){
        leftText =getResources().getString(resId);
        tvLeft.setText(leftText);
    }

    public void setLeftText(String s){
        leftText =s;
        tvLeft.setText(leftText);
    }

    public void setCenterText(@StringRes int resId){
        dayMoney =getResources().getString(resId);
        tvCenter.setText(dayMoney);
    }

    public void setCenterText(String s){
        dayMoney =s;
        tvCenter.setText(dayMoney);
    }

    public void setRightText(@StringRes int resId){
        dayFee =getResources().getString(resId);
        tvRight.setText(dayFee);
    }

    public void setRightText(String s){
        dayFee =s;
        tvRight.setText(dayFee);
    }

    public Drawable getLeftImage(){
        return leftImage;
    }

    public String getLeftText(){
        return leftText;
    }

    public String getCenterText(){
        return dayMoney;
    }

    public String getRightText(){
        return dayFee;
    }

    public TextView getCenterTextView(){
        return tvCenter;
    }

}
