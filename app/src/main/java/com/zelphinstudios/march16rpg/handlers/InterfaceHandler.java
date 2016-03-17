package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;

import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.instances.gui.Button;
import com.zelphinstudios.march16rpg.instances.gui.GUI;
import com.zelphinstudios.march16rpg.instances.gui.TextField;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;

import java.util.Vector;

public class InterfaceHandler extends BaseHandler {

    //region Variables
    private Context context;

    private Player player;

    private Vector<GUI> GUIs = new Vector<>();

    private int open,
            chat;
    //endregion

    //region Constructor
    public InterfaceHandler(Context context_, Player player_) {
        context = context_;
        player = player_;
        bitmapDecoder = new BitmapDecoder(context_);
        GUI gui;
        //region General Button Interface
        gui = new GUI(0, 0, null, true);
        gui.addButton(new Button(164, 628, 144, 144, 100, bitmapDecoder.decode("arrow_north"), bitmapDecoder.decode("arrow_north_press"), true));
        gui.addButton(new Button(308, 772, 144, 144, 101, bitmapDecoder.decode("arrow_east"), bitmapDecoder.decode("arrow_east_press"), true));
        gui.addButton(new Button(164, 916, 144, 144, 102, bitmapDecoder.decode("arrow_south"), bitmapDecoder.decode("arrow_south_press"), true));
        gui.addButton(new Button(20,  772, 144, 144, 103, bitmapDecoder.decode("arrow_west"), bitmapDecoder.decode("arrow_west_press"), true));
        gui.addButton(new Button(0,0,100,100,104,bitmapDecoder.decode("arrow_north"), bitmapDecoder.decode("arrow_north"), true));
        GUIs.addElement(gui);
        //endregion
    }
    //endregion

    //region Methods
    public void updateStatInterface() {
        //TODO: Fix this how I want it
        //GUI gui = GUIs.get(3);
        //gui.getTextField(0).setText("Melee Power:");
        //GUIs.set(3, gui);
    }
    //region Chat System
    public void sendChat(int chat_) {
        GUIs.get(2).setVisible(true);
        open = 2;
        switch(chat_) {
            case 1:
                sendChat("Some Chat...");
                break;
            case 2:
                closeChat();
                break;
        }
        chat = chat_;
    }
    private void closeChat() {
        open = 0;
        GUIs.get(2).setVisible(false);
    }
    private void clearText() {
        for(TextField textField : GUIs.get(2).getTextFields()) {
            textField.setText("");
        }
    }
    private void sendChat(String chat_) {
        clearText();
        GUIs.get(2).getTextField(2).setText(chat_);
    }
    private void sendChat(String chat1_, String chat2_) {
        clearText();
        GUIs.get(2).getTextField(1).setText(chat1_);
        GUIs.get(2).getTextField(3).setText(chat2_);
    }
    private void sendChat(String chat1_, String chat2_, String chat3_) {
        clearText();
        GUIs.get(2).getTextField(1).setText(chat1_);
        GUIs.get(2).getTextField(2).setText(chat2_);
        GUIs.get(2).getTextField(3).setText(chat3_);
    }
    private void sendChat(String chat1_, String chat2_, String chat3_, String chat4_) {
        clearText();
        GUIs.get(2).getTextField(0).setText(chat1_);
        GUIs.get(2).getTextField(1).setText(chat2_);
        GUIs.get(2).getTextField(2).setText(chat3_);
        GUIs.get(2).getTextField(3).setText(chat4_);
    }
    private void sendChat(String chat1_, String chat2_, String chat3_, String chat4_, String chat5_) {
        clearText();
        GUIs.get(2).getTextField(0).setText(chat1_);
        GUIs.get(2).getTextField(1).setText(chat2_);
        GUIs.get(2).getTextField(2).setText(chat3_);
        GUIs.get(2).getTextField(3).setText(chat4_);
        GUIs.get(2).getTextField(4).setText(chat5_);
    }
    //endregion
    //endregion

    //region Getters
    public Vector<GUI> getGUIs() {
        return GUIs;
    }
    public int getOpen() {
        return open;
    }
    public int getChat() {
        return chat;
    }
    //endregion

    //region Setters
    //endregion
}
