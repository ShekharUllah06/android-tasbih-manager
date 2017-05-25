package com.dev_station.dhikrmanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.dev_station.dhikrmanager.R;
import com.dev_station.dhikrmanager.adapter.TasbihItemListAdapter;
import com.dev_station.dhikrmanager.database.Database;
import com.dev_station.dhikrmanager.model.TasbihItem;

import java.util.List;

import static com.dev_station.dhikrmanager.R.id.tvNoItem;

public class ItemListActivity extends AppCompatActivity {

    ListView listView;
    String dhikrType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dhikrType = getIntent().getStringExtra("DhikirType");

        Database.init(ItemListActivity.this);


        final List<TasbihItem> allTasbihItems = Database.getAll(dhikrType);
        TextView tvNoItem1=(TextView)findViewById(tvNoItem);
        if(allTasbihItems.size()>0){
            tvNoItem1.setText(dhikrType+" Item List");
        }else{
            tvNoItem1.setText("No "+dhikrType+" Item Found");
        }


        TasbihItemListAdapter tasbihItemListAdapter = new TasbihItemListAdapter(this,allTasbihItems);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(tasbihItemListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ItemListActivity.this, EditTasbihItemActivity.class);
                //String name = nameArray[position];
                String name=allTasbihItems.get(position).getItemName();
                intent.putExtra("Name of Allah", name);
                int reciteTotal = allTasbihItems.get(position).getTotal();
                intent.putExtra("total", reciteTotal);
                intent.putExtra("DhikirType", dhikrType);
                startActivity(intent);
                finish();

            }
        });

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

                final String name=allTasbihItems.get(position).getItemName();
                final String type=dhikrType;

                AlertDialog.Builder dialog = new AlertDialog.Builder(ItemListActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        TasbihItem t=new TasbihItem();
                        t.setItemName(name);
                        t.setType(type);
                        Database.init(ItemListActivity.this);
                        Database.deleteEntry(t);
                        Intent mintent = new Intent(ItemListActivity.this, MainActivity.class);
                        mintent.putExtra("DhikirType", dhikrType);
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

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_add:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent addItemIntent = new Intent(ItemListActivity.this, AddItemActivity.class);
                addItemIntent.putExtra("DhikirType", dhikrType);
                startActivity(addItemIntent);
                finish();
                return true;

            case android.R.id.home:
                Intent mintent = new Intent(ItemListActivity.this, MainActivity.class);
                startActivity(mintent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
