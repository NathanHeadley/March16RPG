package com.zelphinstudios.march16rpg.instances;

import android.graphics.Bitmap;

public class BaseInstance {

    //region Variables
    protected String name;
    protected int id;
    protected Bitmap bitmap[] = new Bitmap[4];
    //endregion

    //region Constructor
    //endregion
    //region Methods
    //endregion

    //region Getters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public Bitmap getBitmap(int bitmap_) {
        return bitmap[bitmap_];
    }
    //endregion

    //region Setters
    public void setName(String name_) {
        name = name_;
    }
    public void setId(int id_) {
        id = id_;
    }
    public void setBitmap(int id_, Bitmap bitmap_) {
        bitmap[id_] = bitmap_;
    }
    //endregion
}
