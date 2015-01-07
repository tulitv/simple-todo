package com.codepath.simpletodo;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends /*FragmentActivity*/ ActionBarActivity implements EditItemDialog.EditItemDialogListener{

    ArrayList<TodoItem> items;
    TodoItemsAdapter itemsAdapter;
    ListView lvItems;

    private final int REQUEST_CODE_SAVE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActiveAndroid.dispose();
        ActiveAndroid.initialize(this);

        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TodoItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        EditText etDueDate = (EditText) findViewById(R.id.etDueDate);

        // Add new todoItem to db
        TodoItem todoItem = new TodoItem();
        todoItem.itemName = etNewItem.getText().toString();
        todoItem.itemDueDate = etDueDate.getText().toString();
        todoItem.save();

        // Add new todoItem to itemsAdapter
        itemsAdapter.add(todoItem);

        // Reset textfields
        etNewItem.setText("");
        etDueDate.setText("");

        // Set the focus on the Item name
        etNewItem.requestFocus();
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        // not header
                        if (pos!=0) {
                            // remove todoItem from db
                            items.get(pos).delete();

                            //remove todoItem from list
                            items.remove(pos);

                            //notify adapter
                            itemsAdapter.notifyDataSetChanged();
                        }

                        return true;
                    }
                }
        );
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                           View item, int pos, long id) {
                        // not header
                        if (pos!=0) {

                            TodoItem todoItem;
                            todoItem = items.get(pos);
                            showEditDialog(todoItem.itemName, todoItem.itemDueDate, pos);
                        }
                    }
                }
        );
    }

    public void readItems() {

        // read all item from db/table
        items = (ArrayList) getAll();
        TodoItem header = new TodoItem("ITEM NAME", "DUE DATE");
        items.add(0,header);
    }

    public static List<TodoItem> getAll() {
        return new Select()
                .from(TodoItem.class)
                .execute();
    }

    private void showEditDialog(String itemName, String itemDueDate, int pos) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialog editItemDialog = EditItemDialog.newInstance("Edit Item",
                itemName, itemDueDate, pos);
        editItemDialog.show(fm, "fragment_edit_item");
    }

    public void onFinishEditItemDialog(String itemName, String itemDueDate, int pos) {
        // not default value, otherwise something went wrong
        if (pos!=0) {
            TodoItem todoItem = items.get(pos);
            todoItem.itemName = itemName;
            todoItem.itemDueDate = itemDueDate;
            todoItem.save();

            items.remove(pos);
            items.add(pos, todoItem);
            itemsAdapter.notifyDataSetChanged();
        }
    }
}


