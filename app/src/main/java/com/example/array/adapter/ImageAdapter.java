package com.example.array.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.array.ImageItemViewHolder;
import com.example.array.R;
import com.example.array.model.ImageItem;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageItemViewHolder> {
    private List<ImageItem> items;

    public ImageAdapter(List<ImageItem> items) {
        this.items = items;
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new ImageItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        ImageItem item = items.get(position);
//        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
