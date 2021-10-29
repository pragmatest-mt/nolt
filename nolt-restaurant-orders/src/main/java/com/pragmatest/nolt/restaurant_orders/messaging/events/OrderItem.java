package com.pragmatest.nolt.restaurant_orders.messaging.events;

public class OrderItem {

    private String menuItemId;
    private int quantity;
    private String notes;

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

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuItemId='" + menuItemId + '\'' +
                ", quantity=" + quantity +
                ", notes='" + notes + '\'' +
                '}';
    }
}
