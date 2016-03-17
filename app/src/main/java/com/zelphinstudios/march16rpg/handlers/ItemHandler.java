package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;

import com.zelphinstudios.march16rpg.R;
import com.zelphinstudios.march16rpg.instances.Item;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

import java.util.Vector;

public class ItemHandler extends BaseHandler {

    //region Variables
    Player player;
    private Vector<Item> items = new Vector<>();
    private int MAX_ITEMS;
    //endregion

    //region Constructor
    public ItemHandler(Context context_, Player player_) {
        bitmapDecoder = new BitmapDecoder(context_);
        fileLoader = new FileLoader(context_);
        player = player_;
        MAX_ITEMS = context_.getResources().getInteger(R.integer.inventSize);
        int helmet = 0, body = 1, legs = 2, weapon = 3, shield = 4;

        // Loading Items
        items = fileLoader.loadItemList();

    }
    //endregion

    //region Methods
    public boolean isInventoryFull() {
        for(int i : player.getInventory()) {
            if(i == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean hasItem(int id_, int quantity_) {
        for(int i = 0; i < MAX_ITEMS; i++) {
            if(player.getItem(i) == id_) {
                if(player.getItemQuantity(i) >= quantity_) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addItem(int id_, int quantity_) {
        if(!isInventoryFull()) {
            for(int i = 0; i < MAX_ITEMS; i++) {
                if(player.getItem(i) == 0) {
                    player.setInventoryItem(i, id_, quantity_);
                    return true;
                } else if (player.getItem(i) == id_ && items.get(id_).getStackable()) {
                    player.setInventoryItem(i, id_, quantity_ + player.getItemQuantity(i));
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeItem(int id_, int quantity_) {
        if(hasItem(id_, quantity_)) {
            for (int i = 0; i < MAX_ITEMS; i++) {
                if(player.getItem(i) == id_) {
                    if(player.getItemQuantity(i) == quantity_) {
                        player.setInventoryItem(i, 0, 0);
                        return true;
                    } else if(player.getItemQuantity(i) > quantity_) {
                        player.setInventoryItem(i, id_, player.getItemQuantity(i) - quantity_);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //endregion

    //region Getters
    public Vector<Item> getItems() {
        return items;
    }
    public Item getItem(int id_) {
        return items.get(id_);
    }
    //endregion

    //region Setters
    //endregion
}
