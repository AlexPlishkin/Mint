package com.plishkin.alex.mint.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plishkin.alex.mint.Entities.User;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }

    private ArrayList<User> users;

    public MyRecyclerViewAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyRecyclerViewAdapter.ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(users.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
