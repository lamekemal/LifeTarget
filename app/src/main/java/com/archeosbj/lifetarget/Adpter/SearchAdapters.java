package com.archeosbj.lifetarget.Adpter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.R;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;

public class SearchAdapters extends SuggestionsAdapter<Life, SearchAdapters.SearchViewHolders> {
    static class SearchViewHolders extends RecyclerView.ViewHolder {
        protected TextView title, rating, description, adress;

        public SearchViewHolders(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            adress = (TextView) itemView.findViewById(R.id.adress);
            description = (TextView) itemView.findViewById(R.id.description);
            rating = (TextView) itemView.findViewById(R.id.rating);
        }
    }
    public SearchAdapters(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public SearchViewHolders onCreateViewHolder( ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = getLayoutInflater().inflate(R.layout.searchitem, parent,false );
        return new SearchViewHolders(itemView);
    }
    @Override
    public int getItemCount() {
        return getSuggestions().size();
    }
    @Override
    public int getSingleViewHeight() {
        return 100;
    }

    @Override
    public void onBindSuggestionHolder(Life life, SearchViewHolders holder, int position) {
        holder.title.setText(getSuggestions().get(position).getTitle());
        holder.adress.setText(getSuggestions().get(position).getAdress());
        holder.description.setText(getSuggestions().get(position).getDescription());
        holder.rating.setText(getSuggestions().get(position).getRating());
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if(term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<>();
                    for (Life item: suggestions_clone)
                        if(item.getTitle().toLowerCase().contains(term.toLowerCase()))
                            suggestions.add(item);
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<Life>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
