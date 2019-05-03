/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */

package com.archeosbj.lifetarget.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.msgm;
import com.archeosbj.lifetarget.R;

import java.util.List;

class ofMsgViewHolder extends RecyclerView.ViewHolder {
    public TextView providers, msg, information;

    public ofMsgViewHolder(View itemView){
        super(itemView);
        providers = (TextView) itemView.findViewById(R.id.msgof);
        msg = (TextView) itemView.findViewById(R.id.msg);
        //information = (TextView) itemView.findViewById(R.id.description);
    }
}
public class ofMsgAdapter extends RecyclerView.Adapter<ofMsgViewHolder> {
    private Context context;
    private List<msgm> Msg;
    private final msgAdapter.OnItemClickListener listener;

    public ofMsgAdapter(Context context, List<msgm> Msg, msgAdapter.OnItemClickListener listener) {
        this.context = context;
        this.Msg = Msg;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(msgm item);
    }
    @Override
    public ofMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.msgitems, parent,false );
        return new ofMsgViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ofMsgViewHolder holder, final int position) {
        holder.providers.setText(Msg.get(position).getOfmsg());
        holder.msg.setText(Msg.get(position).getMsg());
        // holder.information.setText(Msg.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(Msg.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Msg.size();
    }
}
