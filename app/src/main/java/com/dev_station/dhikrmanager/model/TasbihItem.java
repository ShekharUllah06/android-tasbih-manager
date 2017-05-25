package com.dev_station.dhikrmanager.model;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class TasbihItem {
    String itemName;
    int total;
    String type;

    public TasbihItem() {
    }

    public TasbihItem(String itemName, int total, String type) {
        this.itemName = itemName;
        this.total = total;
        this.type = type;
    }

    public TasbihItem(String itemName, int total) {
        this.itemName = itemName;
        this.total = total;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TasbihItem{" +
                "itemName='" + itemName + '\'' +
                ", total=" + total +
                ", type='" + type + '\'' +
                '}';
    }
}
