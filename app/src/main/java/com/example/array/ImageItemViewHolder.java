package com.example.array;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.array.model.ImageItem;

public class ImageItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public ImageItemViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public void bind(ImageItem item) {
        Glide.with(itemView)
                .load(item.getPath())
                .into(imageView);
    }
}