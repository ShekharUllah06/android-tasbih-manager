package com.dev_station.tasbihmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev_station.tasbihmanager.R;
import com.dev_station.tasbihmanager.database.Database;
import com.dev_station.tasbihmanager.model.TasbihItem;

public class AddItemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtName=(EditText)findViewById(R.id.txtItemName);
                EditText txtTotal=(EditText)findViewById(R.id.txtTotal);

                if(txtName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Item Name!", Toast.LENGTH_LONG).show();
                }else if(txtTotal.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Total!", Toast.LENGTH_LONG).show();
                }else{
                    Database.init(AddItemActivity.this);
                    TasbihItem ti = new TasbihItem();
                    ti.setItemName(txtName.getText().toString());
                    ti.setTotal(Integer.parseInt(txtTotal.getText().toString()));
                    Database.create(ti);
                    Intent mintent = new Intent(AddItemActivity.this, MainActivity.class);
                    startActivity(mintent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent mintent = new Intent(AddItemActivity.this, MainActivity.class);
                startActivity(mintent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
