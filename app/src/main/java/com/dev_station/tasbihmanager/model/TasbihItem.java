package com.dev_station.tasbihmanager.model;

/**
 * Created by Abdullah Shekhar on 5/20/2017.
 */

public class TasbihItem {
    String itemName;
    int total;

    public TasbihItem() {
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

    @Override
    public String toString() {
        return "TasbihItem{" +
                "itemName='" + itemName + '\'' +
                ", total=" + total +
                '}';
    }
}
