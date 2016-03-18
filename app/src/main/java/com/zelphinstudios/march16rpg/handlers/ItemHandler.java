package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.R;
import com.zelphinstudios.march16rpg.entities.ItemEntity;
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
        for(ItemEntity itemEntity : player.getInventory()) {
            if(itemEntity.getId() == -1) {
                return false;
            }
        }
        return true;
    }
    public boolean hasItem(int id_, int quantity_) {
        for(int i = 0; i < MAX_ITEMS; i++) {
            if(player.getItem(i).getId() == id_) {
                if(player.getItem(i).getQuantity() >= quantity_) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addItem(int id_, int quantity_) {
        if(!isInventoryFull()) {
            for(int i = 0; i < MAX_ITEMS; i++) {
                if(player.getItem(i).getId() == -1) {
                    player.setInventoryItem(i, id_, quantity_);
                    return true;
                } else if (player.getItem(i).getId() == id_ && items.get(id_).getStackable()) {
                    player.setInventoryItem(i, id_, quantity_ + player.getItem(i).getQuantity());
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeItem(int id_, int quantity_) {
        Log.e("Nathan", "removeitem begin");
        if(hasItem(id_, quantity_)) {
            Log.e("Nathan", "i has the item.");
            for (int i = 0; i < MAX_ITEMS; i++) {
                Log.e("Nathan", "checking an item");
                if(player.getItem(i).getId() == id_) {
                    Log.e("Nathan", "id is correct.");
                    if(player.getItem(i).getQuantity() == quantity_) {
                        Log.e("Nathan", "Removing item.");
                        player.setInventoryItem(i, -1, 0);
                        Log.e("Nathan", "Removed.");
                        return true;
                    } else if(player.getItem(i).getQuantity() > quantity_) {
                        Log.e("Nathan", "");
                        player.setInventoryItem(i, id_, player.getItem(i).getQuantity() - quantity_);
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
