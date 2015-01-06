package com.codepath.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by vincetulit on 1/6/15.
 */
@Table(name = "Items")
public class TodoItem extends Model {
    @Column(name = "ItemName")
    public String itemName;
    @Column(name = "ItemDueDate")
    public String itemDueDate;

    public TodoItem(){
        super();
    }

    public TodoItem(String itemName, String itemDueDate) {
        super();
        this.itemName = itemName;
        this.itemDueDate = itemDueDate;
    }
}
