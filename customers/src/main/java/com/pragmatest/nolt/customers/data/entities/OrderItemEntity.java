package com.pragmatest.nolt.customers.data.entities;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemEntity {

    private String menuItemId;
    private int quantity;
    private String notes;

    public OrderItemEntity(String menuItemId, int quantity, String notes) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
    }

    public OrderItemEntity(String menuItemId, int quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public OrderItemEntity() {
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