package com.zelphinstudios.march16rpg.instances.gui;

import android.graphics.Bitmap;

import java.util.Vector;

public class GUI extends BaseGUI {

    //region Variables
    private Bitmap background;

    private Vector<Button> buttons = new Vector<>();
    private Vector<TextField> textFields = new Vector<>();
    //endregion

    //region Constructor
    public GUI(int x_, int y_, Bitmap background_, boolean visible_) {
        x = x_;
        y = y_;
        background = background_;
        visible = visible_;
    }
    //endregion

    //region Methods
    public void addButton(Button button_) {
        buttons.addElement(button_);
    }
    public void addTextField(TextField textField_) {
        textFields.addElement(textField_);
    }
    //endregion

    //region Getters
    public Bitmap getBackground() {
        return background;
    }
    public Vector<Button> getButtons() {
        return buttons;
    }
    public Button getButton(int button_) {
        return buttons.get(button_);
    }
    public Vector<TextField> getTextFields() {
        return textFields;
    }
    public TextField getTextField(int textField_) {
        return textFields.get(textField_);
    }
    //endregion

    //region Setters
    public void setBackground(Bitmap background_) {
        background = background_;
    }
    public void setButton(int id_, Button button_) {
        buttons.set(id_, button_);
    }
    public void setTextField(int id_, TextField textField_) {
        textFields.set(id_, textField_);
    }
    //endregion
}
