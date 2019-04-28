package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.archeosbj.lifetarget.R;
import com.archeosbj.lifetarget.imageViewer;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by gab on 2/8/19.
 */

public class InnovImageAdapter extends RecyclerView.Adapter<InnovImageAdapter.ViewHolder> {

    Context context;
    List<Uri> imageList;
    public InnovImageAdapter(Context context, List<Uri> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.innov_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context)
                .load(imageList.get(position))
                .into( holder.image);
        //holder.image.setImageResource(imageList.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, imageViewer.class);
                String[] RestoItm = new String[10];
                RestoItm[9] =  imageList.get(position).toString();
                intent.putExtra("ITEM",RestoItm);
                context.startActivity(intent);
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return imageList.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private LinearLayout linearLayout;
        public ViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.imageInnov);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }


}


