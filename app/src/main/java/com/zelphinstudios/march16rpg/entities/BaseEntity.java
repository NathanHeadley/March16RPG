package com.zelphinstudios.march16rpg.entities;

public class BaseEntity {

    //region Variables
    // Protected allows sub classes to use the variables
    protected int x,
                  y;

    protected int id,
            width,
            height,
            direction;

    protected boolean visible;
    //endregion

    //region Constructor
    //endregion

    //region Methods
    /*no methods*/
    //endregion

    //region Getters
    public int getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getDirection() {
        return direction;
    }
    public boolean isVisible() {
        return visible;
    }
    //endregion

    //region Setters
    public void setId(int id_) {
        id = id_;
    }
    public void setX(int x_) {
        x = x_;
    }
    public void setY(int y_) {
        y = y_;
    }
    public void setWidth(int width_) {
        width = width_;
    }
    public void setHeight(int height_) {
        height = height_;
    }
    public void setDirection(int direction_) {
        direction = direction_;
    }
    public void setVisible(boolean visible_) {
        visible = visible_;
    }
    //endregion
}
