package com.edu.vincenttuan.app_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class FoodAdpater extends RecyclerView.Adapter<FoodAdpater.ViewHolder>  {
    private List<Map<String, String>> foodList;
    private LayoutInflater layoutInflater;

    public FoodAdpater(Context context, List<Map<String, String>> foodList) {
        layoutInflater = LayoutInflater.from(context);
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = layoutInflater.inflate(R.layout.list_item, null);
        ViewHolder holder = new ViewHolder(convertView);
        holder.imageView = convertView.findViewById(R.id.imageView);
        holder.textView = convertView.findViewById(R.id.textView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> food = foodList.get(position);
        holder.imageView.setImageResource(Integer.parseInt(food.get("id")));
        holder.textView.setText(food.get("name"));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
