package com.zelphinstudios.march16rpg.instances.gui;

public class BaseGUI {

    //region Variables
    protected int x,
            y;
    protected boolean visible;
    //endregion

    //region Constructor
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isVisible() {
        return visible;
    }
    //endregion

    //region Setters
    public void setX(int x_) {
        x = x_;
    }
    public void setY(int y_) {
        y = y_;
    }
    public void setVisible(boolean visible_) {
        visible = visible_;
    }
    //endregion
}
