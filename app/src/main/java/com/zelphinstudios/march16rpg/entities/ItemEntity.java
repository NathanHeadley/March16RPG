package com.zelphinstudios.march16rpg.entities;

public class ItemEntity  extends BaseEntity {

    //region Variables
    private int slot,
            quantity;
    //endregion

    //region Constructor
    public ItemEntity(int id_, int slot_, int quantity_) {
        id = id_;
        slot = slot_;
        quantity = quantity_;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getSlot() {
        return slot;
    }
    public int getQuantity() {
        return quantity;
    }
    //endregion

    //region Setters
    public void setSlot(int slot_) {
        slot = slot_;
    }
    public void setQuantity(int quantity_) {
        quantity = quantity_;
    }
    //endregion
}
