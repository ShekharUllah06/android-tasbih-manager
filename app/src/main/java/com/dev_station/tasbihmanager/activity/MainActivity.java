package com.dev_station.tasbihmanager.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.dev_station.tasbihmanager.R;
import com.dev_station.tasbihmanager.adapter.TasbihItemListAdapter;
import com.dev_station.tasbihmanager.database.Database;
import com.dev_station.tasbihmanager.model.TasbihItem;

import java.util.List;

public class MainActivity extends BaseActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Test: ", "This is test");
        Log.d("Name: ", "Log d using");

        Database.init(MainActivity.this);


        final List<TasbihItem> allTasbihItems = Database.getAll();
        if(allTasbihItems.size()>0){
            TextView tvNoItem=(TextView)findViewById(R.id.tvNoItem);
            tvNoItem.setText("Tasbih Item List");
        }


        TasbihItemListAdapter tasbihItemListAdapter = new TasbihItemListAdapter(this,allTasbihItems);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(tasbihItemListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, EditTasbihItemActivity.class);
                //String name = nameArray[position];
                String name=allTasbihItems.get(position).getItemName();
                intent.putExtra("Name of Allah", name);
                int reciteTotal = allTasbihItems.get(position).getTotal();
                intent.putExtra("total", reciteTotal);
                startActivity(intent);
                finish();

            }
        });

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

                final String name=allTasbihItems.get(position).getItemName();

                Builder dialog = new Builder(MainActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("Ok", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Database.init(MainActivity.this);
                        Database.deleteEntry(name);
                        Intent mintent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(mintent);
                        finish();
                    }
                });
                dialog.setNegativeButton("Cancel", new OnClickListener() {
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
                Intent mintent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(mintent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
