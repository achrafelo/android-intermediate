package com.possiblelabs.activeandroidtest.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by possiblelabs on 7/21/15.
 */
@Table(name = "Items")
public class Item extends Model {

    @Column(name = "Name", index = true)
    public String name;

    @Column(name = "Category")
    public Category category;

    public Item() {
        super();
    }

    public Item(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
