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
    private boolean isInventoryFull() {
        for(ItemEntity itemEntity : player.getInventory()) {
            if(itemEntity.getId() == -1) {
                return false;
            }
        }
        return true;
    }
    private int hasSpace() {
        int space = 0;
        for(ItemEntity itemEntity : player.getInventory()) {
            if(itemEntity.getId() == -1) {
                space++;
            }
        }
        return space;
    }
    private boolean hasItem(int id_) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (player.getItem(i).getId() == id_) {
                return true;
            }
        }
        return false;
    }
    private boolean hasItem(int id_, int quantity_) {
        if(items.get(id_).getStackable()) {
            for (int i = 0; i < MAX_ITEMS; i++) {
                if (player.getItem(i).getId() == id_) {
                    if (player.getItem(i).getQuantity() >= quantity_) {
                        return true;
                    }
                }
            }
        } else {
            int stack = 0;
            for (int i = 0; i < MAX_ITEMS; i++) {
                if(player.getItem(i).getId() == id_) {
                    stack++;
                }
            }
            if (stack >= quantity_) {
                return true;
            }
        }
        return false;
    }
    public boolean addItem(int id_, int quantity_) {
        if(!isInventoryFull()) {
            if(items.get(id_).getStackable()) {
                if(hasItem(id_)) {
                    for(int i = 0; i < MAX_ITEMS; i++) {
                        if (player.getItem(i).getId() == id_) {
                            player.setInventoryItem(i, id_, quantity_ + player.getItem(i).getQuantity());
                            return true;
                        }
                    }
                } else {
                    for(int i = 0; i < MAX_ITEMS; i++) {
                        if (player.getItem(i).getId() == -1) {
                            player.setInventoryItem(i, id_, quantity_);
                            return true;
                        }
                    }
                }

            } else {
                if(hasSpace() >= quantity_) {
                    for(int q = 0; q < quantity_; q++) {
                        for (int i = 0; i < MAX_ITEMS; i++) {
                            if (player.getItem(i).getId() == -1) {
                                player.setInventoryItem(i, id_, 1);
                                break;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeItem(int id_, int quantity_) {
        if(hasItem(id_, quantity_)) {
            if (items.get(id_).getStackable()) {
                for (int i = 0; i < MAX_ITEMS; i++) {
                    if (player.getItem(i).getId() == id_) {
                        if (player.getItem(i).getQuantity() == quantity_) {
                            player.setInventoryItem(i, -1, 0);
                            return true;
                        } else if (player.getItem(i).getQuantity() > quantity_) {
                            player.setInventoryItem(i, id_, player.getItem(i).getQuantity() - quantity_);
                            return true;
                        }
                    }
                }
            } else {
                int deleted = quantity_;
                for (int i = 0; i < MAX_ITEMS; i++) {
                    if(deleted > 0) {
                        if(player.getItem(i).getId() == id_) {
                            player.setInventoryItem(i, -1, 0);
                            deleted--;
                        }
                    } else {
                        return true;
                    }
                }
                if(deleted == 0)
                    return true;
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
