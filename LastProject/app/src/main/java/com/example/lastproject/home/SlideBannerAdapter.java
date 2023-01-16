package com.example.lastproject.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lastproject.R;

import java.util.ArrayList;

public class SlideBannerAdapter extends RecyclerView.Adapter<SlideBannerAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Integer> images;


    public SlideBannerAdapter(LayoutInflater inflater, Context context, ArrayList<Integer> images) {
        this.inflater = inflater;
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_imgslide_banner,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(images.get(position)).into(holder.iv_slider);
        final int cnt = position;
        holder.iv_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(images.get(cnt)==0){
                    //Toast.makeText(context, "11", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_slider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_slider=itemView.findViewById(R.id.iv_slider);

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
