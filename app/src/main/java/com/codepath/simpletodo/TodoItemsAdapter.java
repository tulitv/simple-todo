package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vincetulit on 1/6/15.
 */

public class TodoItemsAdapter extends ArrayAdapter<TodoItem> {

    // View lookup cache
    private static class ViewHolder {
        TextView itemName;
        TextView itemDueDate;
    }

    public TodoItemsAdapter(Context context, ArrayList<TodoItem> items) {
            super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItem todoItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.todo_items, parent, false);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemName);
            viewHolder.itemDueDate = (TextView) convertView.findViewById(R.id.itemDueDate);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.itemName.setText(todoItem.itemName);
        viewHolder.itemDueDate.setText(todoItem.itemDueDate);

        // Return the completed view to render on screen
        return convertView;
    }
}
