package com.zelphinstudios.march16rpg.instances.gui;

import android.graphics.Bitmap;

public class Button extends BaseGUI {

    //region Variables
    private int width,
                height,
                action,
                state;

    private Bitmap pressed,
            notPressed;
    //endregion

    //region Constructor
    public Button(int x_, int y_, int width_, int height_, int action_, Bitmap pressed_, Bitmap notPressed_, boolean visible_) {
        x = x_;
        y = y_;
        width = width_;
        height = height_;
        action = action_;
        state = 0;
        pressed = pressed_;
        notPressed = notPressed_;
        visible = visible_;
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
    public int getState() {
        return state;
    }
    public Bitmap getPressed() {
        return pressed;
    }
    public Bitmap getNotPressed() {
        return notPressed;
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
    public void setState(int state_) {
        state = state_;
    }
    public void setPressed(Bitmap pressed_) {
        pressed = pressed_;
    }
    public void setNotPressed(Bitmap notPressed_) {
        notPressed = notPressed_;
    }
    //endregion
}
