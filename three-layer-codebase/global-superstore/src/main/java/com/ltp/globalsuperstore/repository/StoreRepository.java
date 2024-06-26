package com.ltp.globalsuperstore.repository;

import java.util.ArrayList;
import java.util.List;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;

public class StoreRepository {
    List<Item> items = new ArrayList<>();

    public Item getItem(int id){
        return id == Constants.NOT_FOUND ? new Item() : items.get(id);
    }

    public List<Item> getItems(){
        return items;
    }

    public void updateItem(int index, Item item){
        items.set(index, item);
    }

    public void addItem(Item item){
        items.add(item);
    }
}
