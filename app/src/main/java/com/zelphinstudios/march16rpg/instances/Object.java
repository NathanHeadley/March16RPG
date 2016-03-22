package com.zelphinstudios.march16rpg.instances;

import android.graphics.Bitmap;

public class Object extends BaseInstance {

    //region Variables
    private int width,
            height,
            action;

    private boolean walkable;
    //endregion

    //region Constructor
    public Object() {
        id = -1;
        name = "null";
        width = 0;
        height = 0;
        action = -1;
        walkable = false;
        bitmap[0] = null;
        bitmap[1] = null;
        bitmap[2] = null;
        bitmap[3] = null;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getWidth() {
        return width/96;
    }
    public int getHeight() {
        return height/96;
    }
    public int getAction() {
        return action;
    }
    public boolean isWalkable() {
        return walkable;
    }
    //endregion

    //region Setters
    public void setWidth(int width_) {
        width = width_*96;
    }
    public void setHeight(int height_) {
        height = height_*96;
    }
    public void setAction(int action_) {
        action = action_;
    }
    public void setWalkable(boolean walkable_) {
        walkable = walkable_;
    }
    //endregion
}
