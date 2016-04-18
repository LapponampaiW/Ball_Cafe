package com.su.lapponampai_w.ball_cafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apple on 4/18/16.
 */
public class MyAdaptor extends BaseAdapter {

    private Context objContext;
    private String[] foodStrings, priceStrings;
    private int[] sourceint;

    public MyAdaptor(Context objContext, String[] foodStrings, int[] sourceint, String[] priceStrings) {
        this.objContext = objContext;
        this.foodStrings = foodStrings;
        this.sourceint = sourceint;
        this.priceStrings = priceStrings;

    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_listview,parent,false);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(foodStrings[position]);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(sourceint[position]);

        TextView textView1 = (TextView) view.findViewById(R.id.textView3);
        textView1.setText(priceStrings[position]);

        return view;
    }
}
