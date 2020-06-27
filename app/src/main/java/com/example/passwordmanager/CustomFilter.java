package com.example.passwordmanager;

import android.widget.Filter;

import com.example.passwordmanager.model.Item;
import com.example.passwordmanager.ui.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {
    RecyclerViewAdapter adapter;
    List<Item> filterList;

    public CustomFilter(List<Item> filterList, RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();

        if(constraint!=null && constraint.length()>0){
            constraint = constraint.toString().toLowerCase();
            List<Item> filteredItem = new ArrayList<>();
            for(int i=0;i<filterList.size();i++){
                if(filterList.get(i).getWebName().toLowerCase().contains(constraint)){
                    filteredItem.add(filterList.get(i));
                }
            }
            filterResults.count = filteredItem.size();
            filterResults.values = filteredItem;
        }
        else{
            filterResults.count = filterList.size();
            filterResults.values = filterList;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.itemList = (List<Item>) results.values;
        adapter.notifyDataSetChanged();
    }
}
