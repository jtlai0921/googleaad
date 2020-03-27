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

public class FoodAdapterHolder extends BaseAdapter {
    private List<Map<String, String>> foodList;
    private LayoutInflater layoutInflater;

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    public FoodAdapterHolder(Context context, List<Map<String, String>> foodList) {
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
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textView  = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Map<String, String> food = foodList.get(position);

        holder.imageView.setImageResource(Integer.parseInt(food.get("id")));
        holder.textView.setText(food.get("name"));

        return convertView;
    }
}
