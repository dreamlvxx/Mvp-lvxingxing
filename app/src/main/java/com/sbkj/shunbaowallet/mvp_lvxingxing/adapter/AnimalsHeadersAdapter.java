package com.sbkj.shunbaowallet.mvp_lvxingxing.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbkj.shunbaowallet.mvp_lvxingxing.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by lvxingxing on 2017/12/23.
 *
 * @author lvxingxing
 */

public class AnimalsHeadersAdapter extends AnimalsAdapter<RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<AnimalsHeadersAdapter.MyViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(getItem(position));
    }

    /**
     * 实现此方法才能实现分组,如果返回0表示无分组,不同的值才能实现分组,相同的id为一组
     * 如果想实现按时间分组,可以利用日期字符串的hashCode来进行分组
     */
    @Override
    public long getHeaderId(int position) {
        return getItem(position).charAt(0);
    }

    @Override
    public AnimalsHeadersAdapter.MyViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_header, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(AnimalsHeadersAdapter.MyViewHolder holder, int position) {
        holder.tv11.setText(String.valueOf(getItem(position).charAt(0)));
        holder.tv22.setText("第二行数据");
        holder.tv33.setText("第三行数据");
        holder.itemView.setBackgroundColor(Color.RED);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv11;
        TextView tv22;
        TextView tv33;

        private MyViewHolder(View itemView) {
            super(itemView);
            tv11 = (TextView) itemView.findViewById(R.id.tv11);
            tv22 = (TextView) itemView.findViewById(R.id.tv22);
            tv33 = (TextView) itemView.findViewById(R.id.tv33);
        }
    }
}