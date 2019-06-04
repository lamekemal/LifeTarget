package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Serli;
import com.archeosbj.lifetarget.R;
import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

class SerliViewHolder extends RecyclerView.ViewHolder {
    public TextView title, rating, description, adress;
    public ImageView thumbnail;

    public SerliViewHolder(View itemView){
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        adress = (TextView) itemView.findViewById(R.id.adress);
        description = (TextView) itemView.findViewById(R.id.description);
        rating = (TextView) itemView.findViewById(R.id.rating);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }
}

public class SerliAdapter extends RecyclerView.Adapter<SerliViewHolder> {
    private Context context;
    private List<Serli> Hotel;
    private final SerliAdapter.OnItemClickListener listener;

    public SerliAdapter(Context context, List<Serli> Hotel, SerliAdapter.OnItemClickListener listener) {
        this.context = context;
        this.Hotel = Hotel;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(Serli item);
    }
    @Override
    public SerliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.searchitem, parent,false );
        return new SerliViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SerliViewHolder holder, final int position) {
        holder.title.setText(Hotel.get(position).getName());
        holder.adress.setText(Hotel.get(position).getZonelivre());
        holder.description.setText(Hotel.get(position).getDescription());
        holder.rating.setText(Hotel.get(position).getReserveone());

        Storage storage = new Storage(context);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + Hotel.get(position).getPrimpimage();

        //hotelviwer.imageManupulate.setBitmapImageFormMomory(fileph,holder.thumbnail);
        //Bitmap bm = decodeSampledBitmapFromResource(fileph,95,95);
        //holder.thumbnail.setImageBitmap(bm);
        Glide.with(context)
                .load(storage
                .getFile(fileph))
                .into(holder.thumbnail);

        ///////////////////////////////////////////////////////////////////////////////////
        holder.thumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //////////////////////////////////////////////////////////////////////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(Hotel.get(position));
            }
        });
    }
    public static Bitmap decodeSampledBitmapFromResource(String filepath,
                                                         int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (!(options == null)){
            BitmapFactory.decodeFile(filepath, options);

            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;
            if (height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;
                    /*int scale = 1;
                    if (options.outHeight > halfHeight || options.outWidth > halfWidth) {
                        scale = (int)Math.pow(2, (int) Math.ceil(Math.log(halfHeight /
                                (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
                    }*/
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
        }
        return BitmapFactory.decodeFile(filepath, options);
    }
    @Override
    public int getItemCount() {
        return Hotel.size();
    }
}
