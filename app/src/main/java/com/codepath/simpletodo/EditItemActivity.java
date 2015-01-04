package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class EditItemActivity extends ActionBarActivity {

    EditText etEditText;
    String strItem;
    int    idxItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        strItem = getIntent().getStringExtra("strItem");
        idxItem = getIntent().getIntExtra("idxItem", 0);

        etEditText = (EditText) findViewById(R.id.etEditItem);
        etEditText.setText(strItem);
        etEditText.setSelection(strItem.length());
        etEditText.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    public void onSaveItem(View view) {
        strItem = etEditText.getText().toString();

        Intent data = new Intent();
        data.putExtra("strItem", strItem);
        data.putExtra("idxItem", idxItem);

        setResult(RESULT_OK, data);

        finish();
    }
}
