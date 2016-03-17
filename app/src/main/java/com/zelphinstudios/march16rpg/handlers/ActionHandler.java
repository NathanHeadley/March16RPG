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
                if(objectAt(0, -32) < 0) {
                    player.setY(player.getY() - 32);
                }
                player.setDirection(0);
                break;
            case 101:
                if(objectAt(32, 0) < 0) {
                    player.setX(player.getX() + 32);
                }
                player.setDirection(1);
                break;
            case 102:
                if(objectAt(0, 32) < 0) {
                    player.setY(player.getY() + 32);
                }
                player.setDirection(2);
                break;
            case 103:
                if(objectAt(-32, 0) < 0) {
                    player.setX(player.getX() - 32);
                }
                player.setDirection(3);
                break;
            case 104:
                //TODO: Add player direction into interaction
                //FIXME: I can use objects from miles away
                int objectId = -1;
                if(player.getDirection() == 0) {
                    objectId = objectAt(0, -32);
                } else if(player.getDirection() == 1) {
                    objectId = objectAt(32, 0);
                } else if(player.getDirection() == 2) {
                    objectId = objectAt(0, 32);
                } else if(player.getDirection() == 3) {
                    objectId = objectAt(-32, 0);
                }
                if(objectId >= 0) {
                    objectInteract(objectHandler.getObjects().get(objectId).getAction());
                }
                break;
        }
    }

    private void objectInteract(int action_) {
        switch(action_) {
            case 3:
                Log.e("Nathan", "Action 3 carried out.");
                break;
        }
    }

    private int objectAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX > (o.getX() - o.getWidth()) && newX < o.getX() + o.getWidth()
                    && newY > (o.getY() - o.getHeight()) && newY < o.getY() + o.getHeight()) {
                return o.getId();
            }
        }
        return -1;
    }

    //TODO: Improve to handle object width and height
    /*private int objectIdAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(o.getX() == newX && o.getY() == newY) {
                return o.getId();
            }
        }
        return -1;
    }*/
    //endregion
}
