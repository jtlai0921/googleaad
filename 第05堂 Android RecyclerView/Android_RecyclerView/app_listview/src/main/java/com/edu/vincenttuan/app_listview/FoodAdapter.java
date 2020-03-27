package com.edu.vincenttuan.app_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class FoodAdapter extends BaseAdapter {
    private List<Map<String, String>> foodList;
    private LayoutInflater layoutInflater;

    public FoodAdapter(Context context, List<Map<String, String>> foodList) {
        layoutInflater = LayoutInflater.from(context);
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String, String> food = foodList.get(position);
        convertView = layoutInflater.inflate(R.layout.list_item, null);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView  textView  = convertView.findViewById(R.id.textView);
        imageView.setImageResource(Integer.parseInt(food.get("id")));
        textView.setText(food.get("name"));
        return convertView;
    }
}
