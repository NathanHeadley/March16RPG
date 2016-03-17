package com.zelphinstudios.march16rpg.entities;

import com.zelphinstudios.march16rpg.instances.Object;

public class ObjectEntity extends BaseEntity {

    //region Variables
    //endregion

    //region Constructor
    public ObjectEntity(Object object_, int x_, int y_) {
        id = object_.getId();
        x = x_*96;
        y = y_*96;
        width = object_.getWidth();
        height = object_.getHeight();
        direction = 0;
        visible = true;
    }
    public ObjectEntity(int id_, int x_, int y_, boolean visible_) {
        id = id_;
        x = x_*96;
        y = y_*96;
        width = 0;
        height = 0;
        direction = 0;
        visible = visible_;
    }
    public ObjectEntity(int id_, int x_, int y_, int direction_, boolean visible_) {
        id = id_;
        x = x_*96;
        y = y_*96;
        width = 0;
        height = 0;
        direction = direction_;
        visible = visible_;
    }
    //endregion

    //region Methods
    //endregion
    //region Getters
    //endregion
    //region Setters
    //endregion
}
