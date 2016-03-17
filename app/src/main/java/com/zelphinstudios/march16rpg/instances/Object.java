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
    public Object(int id_, String name_, int width_, int height_, int action_, boolean walkable_,
                  Bitmap bitmap0_) {
        id = id_;
        name = name_;
        width = width_;
        height = height_;
        action = action_;
        walkable = walkable_;
        bitmap[0] = bitmap0_;
        bitmap[1] = bitmap0_;
        bitmap[2] = bitmap0_;
        bitmap[3] = bitmap0_;
    }
    public Object(int id_, String name_, int width_, int height_, int action_, boolean walkable_,
                  Bitmap bitmap0_, Bitmap bitmap1_, Bitmap bitmap2_, Bitmap bitmap3_) {
        id = id_;
        name = name_;
        width = width_;
        height = height_;
        action = action_;
        walkable = walkable_;
        bitmap[0] = bitmap0_;
        bitmap[1] = bitmap1_;
        bitmap[2] = bitmap2_;
        bitmap[3] = bitmap3_;
    }
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
        return width;
    }
    public int getHeight() {
        return height;
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
        width = width_;
    }
    public void setHeight(int height_) {
        height = height_;
    }
    public void setAction(int action_) {
        action = action_;
    }
    public void setWalkable(boolean walkable_) {
        walkable = walkable_;
    }
    //endregion
}
