package com.zhimin.share;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder homeHolder, int position) {
        homeHolder.textView.setText("TextView  这是条目的第 " + position + " 条");
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class HomeHolder extends RecyclerView.ViewHolder{

        TextView textView;

        HomeHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView2);
        }
    }
}
