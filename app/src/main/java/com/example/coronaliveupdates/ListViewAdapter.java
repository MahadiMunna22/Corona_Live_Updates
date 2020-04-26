package com.example.coronaliveupdates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CountryNames> countryNamesList = null;
    private ArrayList<CountryNames> arraylist;

    public ListViewAdapter(Context context, List<CountryNames> countryNamesList) {
        mContext = context;
        this.countryNamesList = countryNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<CountryNames>();
        this.arraylist.addAll(countryNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return countryNamesList.size();
    }

    @Override
    public CountryNames getItem(int position) {
        return countryNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(countryNamesList.get(position).getCountryName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        countryNamesList.clear();
        if (charText.length() == 0) {
            countryNamesList.addAll(arraylist);
        } else {
            for (CountryNames wp : arraylist) {
                if (wp.getCountryName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    countryNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

