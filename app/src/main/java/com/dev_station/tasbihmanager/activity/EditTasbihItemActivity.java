package com.dev_station.tasbihmanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditTasbihItemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tasbih_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getStringExtra("Name of Allah");
        EditText txtName = (EditText) findViewById(R.id.txtItemName);
        txtName.setText(name);
        txtName.setEnabled(false);

        int total = getIntent().getIntExtra("total",0);
        EditText txtTotal = (EditText) findViewById(R.id.txtTotal);
        txtTotal.setText(String.valueOf(total));
        txtTotal.setEnabled(false);

        final Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtName=(EditText)findViewById(R.id.txtItemName);
                EditText txtTotal=(EditText)findViewById(R.id.txtTotal);
                EditText txtNewRecited=(EditText)findViewById(R.id.txtNewRecited);

                if(txtNewRecited.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter New Recited!", Toast.LENGTH_LONG).show();
                }else{
                    Database.init(EditTasbihItemActivity.this);
                    TasbihItem ti = new TasbihItem();
                    ti.setItemName(txtName.getText().toString());
                    ti.setTotal(Integer.parseInt(txtTotal.getText().toString())+Integer.parseInt(txtNewRecited.getText().toString()));
                    Database.update(ti);
                    Intent mintent = new Intent(EditTasbihItemActivity.this, MainActivity.class);
                    startActivity(mintent);
                    finish();
                }
            }
        });

        final Button btnReset = (Button)findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(EditTasbihItemActivity.this);
                dialog.setTitle("Reset");
                dialog.setMessage("Reset this item?");
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText txtName=(EditText)findViewById(R.id.txtItemName);
                        EditText txtTotal=(EditText)findViewById(R.id.txtTotal);
                        EditText txtNewRecited=(EditText)findViewById(R.id.txtNewRecited);


                            Database.init(EditTasbihItemActivity.this);
                            TasbihItem ti = new TasbihItem();
                            ti.setItemName(txtName.getText().toString());
                            ti.setTotal(0);
                            Database.update(ti);
                            Intent mintent = new Intent(EditTasbihItemActivity.this, MainActivity.class);
                            startActivity(mintent);
                            finish();

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent mintent = new Intent(EditTasbihItemActivity.this, MainActivity.class);
                startActivity(mintent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
