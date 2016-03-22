package com.zelphinstudios.march16rpg.entities;

import android.util.Log;

import com.zelphinstudios.march16rpg.instances.Object;

public class ObjectEntity extends BaseEntity {

    //region Variables
    //endregion

    //region Constructor
    public ObjectEntity(Object object_, int x_, int y_) {
        id = object_.getId();
        x = x_*96;
        y = y_*96;
        width = object_.getWidth()*96;
        height = object_.getHeight()*96;
        direction = 0;
        visible = true;
    }
    //endregion

    //region Methods
    //endregion
    //region Getters
    //endregion
    //region Setters
    //endregion
}
