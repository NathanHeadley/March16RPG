package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.entities.ObjectEntity;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;

public class ActionHandler extends BaseHandler {

    //region Variables
    private Context context;
    private Player player;
    private InterfaceHandler interfaceHandler;
    private ObjectHandler objectHandler;
    private NPCHandler npcHandler;
    private TerrainHandler terrainHandler;
    private ItemHandler itemHandler;
    //endregion

    //region Constructor
    public ActionHandler(Context context_, Player player_, InterfaceHandler interfaceHandler_,
                         ObjectHandler objectHandler_, NPCHandler npcHandler_,
                         TerrainHandler terrainHandler_, ItemHandler itemHandler_) {
        context = context_;
        bitmapDecoder = new BitmapDecoder(context);
        player = player_;
        interfaceHandler = interfaceHandler_;
        objectHandler = objectHandler_;
        npcHandler = npcHandler_;
        terrainHandler = terrainHandler_;
        itemHandler = itemHandler_;
    }
    //endregion

    //region Methods
    public void action(int actionId_) {
        switch(actionId_) {
            case 100: // A button
                if(!objectAt(0, -32)) {
                    player.setY(player.getY() - 32);
                }
                break;
            case 101:
                if(!objectAt(32, 0)) {
                    player.setX(player.getX() + 32);
                }
                break;
            case 102:
                if(!objectAt(0, 32)) {
                    player.setY(player.getY() + 32);
                }
                break;
            case 103:
                if(!objectAt(-32, 0)) {
                    player.setX(player.getX() - 32);
                }
                break;
            case 104:
                //press X
                break;
        }
    }

    private boolean objectAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX > (o.getX() - o.getWidth()) && newX < o.getX() + o.getWidth()
                    && newY > (o.getY() - o.getHeight()) && newY < o.getY() + o.getHeight()) {
                return true;
            }
        }
        return false;
    }
    //endregion
}
