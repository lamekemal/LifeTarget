/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */

package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.GeneralModel;
import com.archeosbj.lifetarget.R;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.List;

import static com.archeosbj.lifetarget.Adpter.HotelAdapter.decodeSampledBitmapFromResource;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

class  GeneralMViewHolder extends RecyclerView.ViewHolder {
    public TextView title, rating, description, adress;
    public ImageView thumbnail;

    public  GeneralMViewHolder(View itemView){
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        adress = (TextView) itemView.findViewById(R.id.adress);
        description = (TextView) itemView.findViewById(R.id.description);
        rating = (TextView) itemView.findViewById(R.id.rating);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }
}

public class GeneralMAdapter extends RecyclerView.Adapter< GeneralMViewHolder>{
    private Context context;
    private List<GeneralModel> GM;
    private final GeneralMAdapter.OnItemClickListener listener;

    public GeneralMAdapter(Context context, List<GeneralModel> generalModel, OnItemClickListener listener) {
        this.context = context;
        this.GM = generalModel;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(GeneralModel item);
    }

    @Override
    public GeneralMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.searchitem, parent,false );
        return new GeneralMViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( GeneralMViewHolder holder, final int position) {
        holder.title.setText(GM.get(position).getTitle());
        holder.adress.setText(GM.get(position).getAdress());
        holder.description.setText(GM.get(position).getDescription());
        holder.rating.setText(GM.get(position).getRating());

        Storage storage = new Storage(context);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + GM.get(position).getUrlimage();
        //hotelviwer.imageManupulate.setBitmapImageFormMomory(fileph,holder.thumbnail);
        Bitmap bm = decodeSampledBitmapFromResource(fileph,95,95);
        holder.thumbnail.setImageBitmap(bm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(GM.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return GM.size();
    }
}
