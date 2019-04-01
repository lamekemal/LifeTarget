package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Destination;
import com.archeosbj.lifetarget.R;
import java.util.List;

/**
 * Created by gab on 2/8/19.
 */

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    Context context;
    List<Destination> destinationList;
    public DestinationAdapter(Context context, List<Destination> destinationList){
        this.context = context;
        this.destinationList = destinationList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.destination_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nom.setText(destinationList.get(position).getNom());
        holder.tarif.setText(destinationList.get(position).getTarif());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProduct(v, produit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinationList.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nom;
        private TextView tarif;
        private LinearLayout linearLayout;
        public ViewHolder(View view){
            super(view);
            nom = view.findViewById(R.id.nameDestination);
            tarif = view.findViewById(R.id.tarifDestination);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }


}


