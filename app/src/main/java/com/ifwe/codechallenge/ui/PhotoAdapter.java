package com.ifwe.codechallenge.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifwe.codechallenge.R;
import com.ifwe.codechallenge.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirig on 2/21/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> photoList;
    private Context context;

    public PhotoAdapter(Context context){
        this.context = context;
        photoList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setData(List<Photo> list){
        this.photoList = list;
        notifyDataSetChanged();
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .grid_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        String url = photo.getUrlM();
        Picasso.with(context).load(url).error(R.drawable.image)
                .placeholder(R.drawable.image).into(holder.imageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.description);
        }


    }

}
