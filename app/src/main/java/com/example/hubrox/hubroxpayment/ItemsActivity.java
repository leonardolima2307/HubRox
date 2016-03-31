package com.example.hubrox.hubroxpayment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AlertDialog.Builder dialogBuilder;
    SQLController sqlController;
    TableLayout tableLayout;
    ArrayList<Integer> selectedItems = new ArrayList<>();
    int curSelItem;

    private void addDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        Context context = this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.add_dialog, null);
        dialogBuilder.setView(v);
        final EditText itemCodeEditText = (EditText) v.findViewById(R.id.itemCodeEditText);
        final EditText descriptionEditText = (EditText) v.findViewById(R.id.descriptionEditText);
        final EditText priceEditText = (EditText) v.findViewById(R.id.priceEditText);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ItemsActivity.this.sqlController.open();
                ItemsActivity.this.sqlController.updateItemData(ItemsActivity.this.curSelItem, itemCodeEditText.getText().toString(), descriptionEditText.getText().toString(), Float.parseFloat(priceEditText.getText().toString()));
                ItemsActivity.this.sqlController.close();
                ItemsActivity.this.tableLayout.removeAllViews();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialogAdd = dialogBuilder.create();
        dialogAdd.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.tableLayout = ((TableLayout) findViewById(R.id.itemsTableLayout));
//        BuildTable();
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });

////        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
////        add.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                addDialog();
////            }
////        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void BuildTable() {
        this.sqlController.open();
        Cursor localCursor = this.sqlController.readItemsEntry();
        int i = localCursor.getCount();
        int j = localCursor.getColumnCount();
        localCursor.moveToFirst();
        TableRow localTableRow1 = new TableRow(this);
        String[] arrayOfString = {"", "Item", "Description", "Price"};
        int m;
        for (int k = 0; ; k++) {
            if (k >= arrayOfString.length) {
                this.tableLayout.addView(localTableRow1);
                m = 0;
                if (m < i) {
                    break;
                }
                this.sqlController.close();
                return;
            }
            localTableRow1.setLayoutParams(new TableRow.LayoutParams(-1, -2));
            TextView localTextView1 = new TextView(this);
            localTextView1.setLayoutParams(new TableRow.LayoutParams(-2, -2));
//            localTextView1.setBackgroundResource(2130837504);
            localTextView1.setGravity(17);
            localTextView1.setTextSize(12.0F);
            localTextView1.setPadding(0, 5, 0, 5);
            localTextView1.setText(arrayOfString[k]);
            localTableRow1.addView(localTextView1);
        }
        TableRow localTableRow2 = new TableRow(this);
        localTableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
        CheckBox localCheckBox = new CheckBox(this);
        localCheckBox.setLayoutParams(new TableRow.LayoutParams(-2, -2));
//        localCheckBox.setBackgroundResource(2130837504);
        localCheckBox.setGravity(17);
        localCheckBox.setId(localCursor.getInt(0));
        localCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ItemsActivity localItemsActivity = null;
                if (((CheckBox) paramAnonymousView).isChecked()) {
                    ItemsActivity.this.selectedItems.add(Integer.valueOf(paramAnonymousView.getId()));
                    localItemsActivity = ItemsActivity.this;
                    if (ItemsActivity.this.selectedItems.size() != 0) {
//                        break label175;
                    }
                }
                label173:
                label175:
                for (int j = -1; ; j = ((Integer) ItemsActivity.this.selectedItems.get(-1 + ItemsActivity.this.selectedItems.size())).intValue()) {
                    localItemsActivity.curSelItem = j;
                    Context localContext = ItemsActivity.this.getApplicationContext();
                    Object[] arrayOfObject = new Object[2];
                    arrayOfObject[0] = Integer.valueOf(paramAnonymousView.getId());
                    arrayOfObject[1] = Integer.valueOf(ItemsActivity.this.curSelItem);
                    Toast.makeText(localContext, String.format("%d : %d", arrayOfObject), 0).show();
                    return;
//                    for (int i = 0; ; i++) {
//                        if (i >= ItemsActivity.this.selectedItems.size()) {
//                            break label173;
//                        }
//                        if (((Integer) ItemsActivity.this.selectedItems.get(i)).intValue() == paramAnonymousView.getId()) {
//                            ItemsActivity.this.selectedItems.remove(i);
//                            break;
//                        }
//                    }
//                    break;
                }
            }
        });
        localTableRow2.addView(localCheckBox);
        for (int n = 1; ; n++) {
            if (n >= j) {
                localCursor.moveToNext();
                this.tableLayout.addView(localTableRow2);
                m++;
                break;
            }
            TextView localTextView2 = new TextView(this);
            localTextView2.setLayoutParams(new TableRow.LayoutParams(-2, -2));
//            localTextView2.setBackgroundResource(2130837504);
            localTextView2.setGravity(17);
            localTextView2.setTextSize(12.0F);
            localTextView2.setPadding(0, 5, 0, 5);
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(localCursor.getInt(0));
            arrayOfObject[1] = Integer.valueOf(n);
            localTextView2.setTag(String.format("item_%d_%d", arrayOfObject));
            localTextView2.setText(localCursor.getString(n));
            localTableRow2.addView(localTextView2);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.items, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_items) {
            startActivity(new Intent(this, ItemsActivity.class));
        } else if (id == R.id.nav_payments) {
            startActivity(new Intent(this, PaymentsActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
