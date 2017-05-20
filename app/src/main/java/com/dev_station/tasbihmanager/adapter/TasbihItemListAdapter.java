package com.dev_station.tasbihmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev_station.tasbihmanager.R;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class TasbihItemListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the animal images
    private final int total;

    //to store the list of Tashbih item
    private final String[] nameArray;

    public TasbihItemListAdapter(Activity context, String[] nameArrayParam, int totalParam){

        super(context, R.layout.list_item , nameArrayParam);

        this.context=context;
        this.total = totalParam;
        this.nameArray = nameArrayParam;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.tvItemName);
        TextView totalTextField = (TextView) rowView.findViewById(R.id.tvTotal);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        totalTextField.setText(String.valueOf(total));

        return rowView;

    };


}
