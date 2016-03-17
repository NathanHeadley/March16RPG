package com.zelphinstudios.march16rpg.entities;

public class ObjectEntity extends BaseEntity {

    //region Variables
    //endregion

    //region Constructor
    public ObjectEntity(int id_, float x_, float y_) {
        id = id_;
        x = x_;
        y = y_;
        width = 0;
        height = 0;
        direction = 0;
        visible = true;
    }
    public ObjectEntity(int id_, float x_, float y_, boolean visible_) {
        id = id_;
        x = x_;
        y = y_;
        width = 0;
        height = 0;
        direction = 0;
        visible = visible_;
    }
    public ObjectEntity(int id_, float x_, float y_, int direction_, boolean visible_) {
        id = id_;
        x = x_;
        y = y_;
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
