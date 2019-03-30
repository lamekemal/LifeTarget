package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Actu;
import com.archeosbj.lifetarget.R;
import com.bumptech.glide.Glide;

import java.util.List;

class feedViewHolder extends RecyclerView.ViewHolder {
    public TextView title, username, pubdate, description;
    public ImageView userimg, pubimg;

    public feedViewHolder(View itemView){
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        username = (TextView) itemView.findViewById(R.id.user_name);
        description = (TextView) itemView.findViewById(R.id.description);
        pubdate = (TextView) itemView.findViewById(R.id.publication_date);
        userimg = (ImageView) itemView.findViewById(R.id.user_profil);
        pubimg = (ImageView) itemView.findViewById(R.id.publication_img);
    }
}

public class feedAdapter extends RecyclerView.Adapter<feedViewHolder>  {
    private Context context;
    private List<Actu> life;

    public feedAdapter(Context context, List<Actu> life) {
        this.context = context;
        this.life = life;
    }

    @Override
    public feedViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fee_itm, parent,false );
        return new feedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( feedViewHolder holder, int position) {
        holder.title.setText(life.get(position).getTitle());
        holder.username.setText(life.get(position).getUsername());
        holder.description.setText(life.get(position).getDescription());
        holder.pubdate.setText(life.get(position).getPubdate());
        Glide.with(context).load(life.get(position).getUserimg()).into( holder.userimg);
        Glide.with(context).load(life.get(position).getPubimg()).into( holder.pubimg);
    }

    @Override
    public int getItemCount() {
        return life.size();
    }
}
