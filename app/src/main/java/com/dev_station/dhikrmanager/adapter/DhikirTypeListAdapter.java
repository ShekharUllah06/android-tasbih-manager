package com.dev_station.dhikrmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev_station.dhikrmanager.R;

/**
 * Created by Abdullah Shekhar on 5/25/2017.
 */

public class DhikirTypeListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    String[] dhikrTypes;
    Integer[] total;


    public DhikirTypeListAdapter(Activity context, String[] dhikrTypes,Integer[] total){

        super(context, R.layout.list_item , dhikrTypes);

        this.context=context;

        this.dhikrTypes = dhikrTypes;
        this.total=total;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_dhikr_type, null,true);


        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.tvItemName);
        TextView totalTextField = (TextView) rowView.findViewById(R.id.tvItemTotal);

        //this code sets the values of the objects to values from the arrays

        nameTextField.setText(dhikrTypes[position]);
        totalTextField.setText(String.valueOf(total[position]));

        return rowView;

    };

}
