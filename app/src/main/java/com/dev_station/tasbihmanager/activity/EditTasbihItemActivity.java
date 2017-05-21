package com.dev_station.tasbihmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dev_station.tasbihmanager.R;

public class EditTasbihItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tasbih_item);

        String name = getIntent().getStringExtra("Name of Allah");
        TextView tvName = (TextView) findViewById(R.id.tvItemName);
        tvName.setText(name);

        int total = getIntent().getIntExtra("total",0);
        TextView tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTotal.setText(String.valueOf(total));
    }
}
