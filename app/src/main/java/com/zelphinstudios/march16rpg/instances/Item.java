package com.zelphinstudios.march16rpg.instances;

import android.graphics.Bitmap;

public class Item extends BaseInstance {

    //region Variables
    private int value,
            meleePower,
            rangePower,
            defence,
            equipSlot;
    private boolean stackable;
    //endregion

    //region Constructor
    public Item() {
        id = -1;
        name = "null";
        value = 0;
        meleePower = 0;
        rangePower = 0;
        defence = 0;
        equipSlot = -1;
        stackable = false;
        bitmap[0] = null;
        bitmap[1] = null;
        bitmap[2] = null;
        bitmap[3] = null;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getValue() {
        return value;
    }
    public int getMeleePower() {
        return meleePower;
    }
    public int getRangePower() {
        return rangePower;
    }
    public int getDefence() {
        return defence;
    }
    public int getEquipSlot() {
        return equipSlot;
    }
    public boolean getStackable() {
        return stackable;
    }
    //endregion

    //region Setters
    public void setValue(int value_) {
        value = value_;
    }
    public void setMeleePower(int meleePower_) {
        meleePower = meleePower_;
    }
    public void setRangePower(int rangePower_) {
        rangePower = rangePower_;
    }
    public void setDefence(int defence_) {
        defence = defence_;
    }
    public void setEquipSlot(int equipSlot_) {
        equipSlot = equipSlot_;
    }
    public void setStackable(boolean stackable_) {
        stackable = stackable_;
    }
    //endregion
}
