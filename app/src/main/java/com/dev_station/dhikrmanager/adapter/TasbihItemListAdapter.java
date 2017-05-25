package com.dev_station.dhikrmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev_station.dhikrmanager.R;
import com.dev_station.dhikrmanager.model.TasbihItem;

import java.util.List;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class TasbihItemListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    List<TasbihItem> tasbihItems;


    public TasbihItemListAdapter(Activity context, List<TasbihItem> tasbihItems){

        super(context, R.layout.list_item , tasbihItems);

        this.context=context;

        this.tasbihItems=tasbihItems;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item, null,true);


        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.tvItemName);
        TextView totalTextField = (TextView) rowView.findViewById(R.id.tvTotal);

        //this code sets the values of the objects to values from the arrays

        nameTextField.setText(tasbihItems.get(position).getItemName());
        totalTextField.setText(String.valueOf(tasbihItems.get(position).getTotal()));

        return rowView;

    };


}
