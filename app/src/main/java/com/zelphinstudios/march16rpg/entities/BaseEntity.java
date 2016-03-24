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
        return x/96;
    }
    public int getY() {
        return y/96;
    }
    public int getAbsX() {
        return x;
    }
    public int getAbsY() {
        return y;
    }
    public int getWidth() {
        return width/96;
    }
    public int getHeight() {
        return height/96;
    }
    public int getAbsWidth() {
        return width;
    }
    public int getAbsHeight() {
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
        x = x_*96;
    }
    public void setY(int y_) {
        y = y_*96;
    }
    public void changeX(int x_) {
        x += x_ * 32;
    }
    public void changeY(int y_) {
        y += y_ * 32;
    }
    public void setWidth(int width_) {
        width = width_*96;
    }
    public void setHeight(int height_) {
        height = height_*96;
    }
    public void setDirection(int direction_) {
        direction = direction_;
    }
    public void setVisible(boolean visible_) {
        visible = visible_;
    }
    //endregion
}
