package com.zelphinstudios.march16rpg.instances.gui;

import android.graphics.Color;
import android.graphics.Paint;

public class TextField extends BaseGUI {

    //region Variables
    private String text = "";
    private Paint paint = new Paint();
    //endregion

    //region Constructor
    public TextField(int x_, int y_, String text_, boolean visible_) {
        x = x_;
        y = y_;
        text = text_;
        paint.setTextSize(40); //TODO: Set default text size in values file?
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        visible = visible_;
    }
    public TextField(int x_, int y_, String text_, Paint paint_, boolean visible_) {
        x = x_;
        y = y_;
        text = text_;
        paint = paint_;
        visible = visible_;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public String getText() {
        return text;
    }
    public Paint getPaint() {
        return paint;
    }
    //endregion

    //region Setters
    public void setText(String text_) {
        text = text_;
    }
    public void setPaint(Paint paint_) {
        paint = paint_;
    }
    //endregion
}
