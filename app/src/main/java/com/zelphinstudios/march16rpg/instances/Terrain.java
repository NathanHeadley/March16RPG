package com.zelphinstudios.march16rpg.instances;

import android.graphics.Bitmap;

public class Terrain extends BaseInstance {

    //region Variables
    private boolean visible,
            walkable;
    //endregion

    //region Constructor
    public Terrain() {
        id = -1;
        name = "null";
        visible = true;
        walkable = true;
        bitmap[0] = null;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public boolean isVisible() {
        return visible;
    }
    public boolean isWalkable() {
        return walkable;
    }
    //endregion

    //region Setters
    public void setVisible(boolean visible_) {
        visible = visible_;
    }
    public void setWalkable(boolean walkable_) {
        walkable = walkable_;
    }
    //endregion
}
