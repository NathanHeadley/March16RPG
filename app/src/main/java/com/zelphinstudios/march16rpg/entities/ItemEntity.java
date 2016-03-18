package com.zelphinstudios.march16rpg.entities;

public class ItemEntity  extends BaseEntity {

    //region Variables
    private int quantity;
    //endregion

    //region Constructor
    public ItemEntity(int id_, int quantity_) {
        id = id_;
        quantity = quantity_;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getQuantity() {
        return quantity;
    }
    //endregion

    //region Setters
    public void setQuantity(int quantity_) {
        quantity = quantity_;
    }
    //endregion
}
