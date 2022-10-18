package com.carte.navigator.menu.adapters;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carte.navigator.menu.models.PlaceApi;

import java.util.ArrayList;

public class Adapter_PlaceAutoSuggest extends ArrayAdapter  implements Filterable {

    ArrayList<String> results;
    int resource;
    Context context;
    PlaceApi placeApi = new PlaceApi();

    public Adapter_PlaceAutoSuggest(Context context, int resID)
    {
        super(context, resID);
        this.context = context;
        this.resource = resID;
        Log.d("myFilter", "Adapter_PlaceAutoSuggest: ");
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return results.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Log.d("myFilter", "getFilter: ");
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint!=null)
                {
                    results = placeApi.autoComplete(constraint.toString());

                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(filterResults!=null && filterResults.count>0){
                    notifyDataSetChanged();;
                }else
                {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
