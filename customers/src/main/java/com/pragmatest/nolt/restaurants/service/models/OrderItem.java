package com.pragmatest.nolt.customers.service.models;

public class OrderItem {

    public String menuItemId;
    public int quantity;
    public String notes;

    public OrderItem(String menuItemId, int quantity, String notes) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
    }

    public OrderItem(String menuItemId, int quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
