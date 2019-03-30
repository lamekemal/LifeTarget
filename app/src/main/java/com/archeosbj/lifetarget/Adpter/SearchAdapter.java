package com.archeosbj.lifetarget.Adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {
    public TextView title, rating, description, adress;

    public SearchViewHolder(View itemView){
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        adress = (TextView) itemView.findViewById(R.id.adress);
        description = (TextView) itemView.findViewById(R.id.description);
        rating = (TextView) itemView.findViewById(R.id.rating);
    }

}



public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context;
    private List<Life> life;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Life item);
    }
    public SearchAdapter(Context context, List<Life> life, OnItemClickListener listener) {
        this.context = context;
        this.life = life;
        this.listener = listener;
    }


    @Override
    public SearchViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.searchitem, parent,false );
        return new SearchViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.title.setText(life.get(position).getTitle());
        holder.adress.setText(life.get(position).getAdress());
        holder.description.setText(life.get(position).getDescription());
        holder.rating.setText(life.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(life.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return life.size();
    }
}
