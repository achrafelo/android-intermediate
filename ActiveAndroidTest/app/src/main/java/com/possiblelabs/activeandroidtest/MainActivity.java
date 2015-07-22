package com.possiblelabs.activeandroidtest;

import android.app.Activity;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.possiblelabs.activeandroidtest.model.Category;
import com.possiblelabs.activeandroidtest.model.Item;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertByOne();
        insertMutiple();
    }

    public void insertByOne() {
        Category restaurants = new Category("Restaurantes");
        restaurants.save();

        Item item1 = new Item("CasaDeCampo", restaurants);
        item1.save();

        Item item2 = new Item("TropicalChicken", restaurants);
        item2.save();

        Item item3 = new Item("Dumbo", restaurants);
        item3.save();
    }

    public void insertMutiple() {
        ActiveAndroid.beginTransaction();
        try {
            Category restaurants = new Category("Restaurantes");
            restaurants.save();

            for (int i = 0; i < 100; i++) {
                Item item = new Item("Restaurante " + i, restaurants);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void deleteOne() {
        //Delete 1
        Item item = Item.load(Item.class, 1L);
        item.delete();

        //Delete 2
        Item.delete(Item.class, 1L);
    }

    public void deleteByQuery() {
        new Delete().from(Item.class).where("Id = ?", 1L).execute();
    }

    public static Item getRandom() {
        return new Select().from(Item.class).orderBy("RANDOM()").executeSingle();
    }

    public static Item getRandom(Category category) {
        return new Select()
                .from(Item.class)
                .where("Category = ?", category.getId())
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static List<Item> getAll(Category category) {
        return new Select()
                .from(Item.class)
                .where("Category = ?", category.getId())
                .orderBy("Name ASC")
                .execute();
    }

    //Pre carga de datos
    //https://github.com/pardom/ActiveAndroid/wiki/Pre-populated-databases
}
