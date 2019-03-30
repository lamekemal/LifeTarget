package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.Model.resto;
import com.archeosbj.lifetarget.R;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

class RestoViewHolder extends RecyclerView.ViewHolder {
    public TextView title, rating, description, adress;
    public ImageView thumbnail;

    public RestoViewHolder(View itemView){
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        adress = (TextView) itemView.findViewById(R.id.adress);
        description = (TextView) itemView.findViewById(R.id.description);
        rating = (TextView) itemView.findViewById(R.id.rating);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }
}


public class RestoAdapter extends RecyclerView.Adapter<RestoViewHolder> {
    private Context context;
    private List<resto> Resto;
    private final OnItemClickListener listener;
    public RestoAdapter(Context context, List<resto> Resto, OnItemClickListener listener) {
        this.context = context;
        this.Resto = Resto;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(resto item);
    }
    @Override
    public RestoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.searchitem, parent,false );
        return new RestoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestoViewHolder holder, final int position) {
        holder.title.setText(Resto.get(position).getTitle());
        holder.adress.setText(Resto.get(position).getAdress());
        holder.description.setText(Resto.get(position).getDescription());
        holder.rating.setText(Resto.get(position).getRating());

        Storage storage = new Storage(context);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + Resto.get(position).getPrinpimage();
        Bitmap bm = BitmapFactory.decodeFile(fileph);
        holder.thumbnail.setImageBitmap(bm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(Resto.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Resto.size();
    }
}
