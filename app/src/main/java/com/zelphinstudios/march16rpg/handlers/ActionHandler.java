package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.entities.ObjectEntity;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

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
    public ActionHandler(Context context_, Player player_,  InterfaceHandler interfaceHandler_,
                         ObjectHandler objectHandler_,      NPCHandler npcHandler_,
                         TerrainHandler terrainHandler_,    ItemHandler itemHandler_) {
        context = context_;
        bitmapDecoder = new BitmapDecoder(context);
        fileLoader = new FileLoader(context);
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
            case 100:
                fileLoader.savePlayerFile(player);
                /*if(objectAt(0, -1) < 0) {
                    player.changeY(-1);
                }
                player.setDirection(0);*/
                break;

            case 101:
                itemHandler.addItem(2, 1);
                if(objectAt(1, 0) < 0) {
                    player.changeX(1);
                }
                player.setDirection(1);
                break;

            case 102:
                if(objectAt(0, 1) < 0) {
                    player.changeY(1);
                }
                player.setDirection(2);
                break;

            case 103:
                if(objectAt(-1, 0) < 0) {
                    player.changeX(-1);
                }
                player.setDirection(3);
                break;

            case 104:
                if(interfaceHandler.getOpen() == 0) {
                    int npcId = -1;
                    int objectId = -1;
                    if (player.getDirection() == 0) {
                        npcId = npcIdAt(0, -1);
                        objectId = objectIdAt(0, -1);
                    } else if (player.getDirection() == 1) {
                        npcId = npcIdAt(1, 0);
                        objectId = objectIdAt(1, 0);
                    } else if (player.getDirection() == 2) {
                        npcId = npcIdAt(0, 1);
                        objectId = objectIdAt(0, 1);
                    } else if (player.getDirection() == 3) {
                        npcId = npcIdAt(-1, 0);
                        objectId = objectIdAt(-1, 0);
                    }
                    if (objectId >= 0) {
                        objectInteract(objectHandler.getObjects().get(objectId).getAction());
                    }
                    if (npcId >= 0) {
                        interfaceHandler.sendChat(npcHandler.getNpcs().get(npcId).getChat());
                    }
                } else {
                    interfaceHandler.sendChat(interfaceHandler.getChat() + 1);
                }
                break;
        }
    }

    private void objectInteract(int action_) {
        switch(action_) {
            case 3:
                interfaceHandler.sendChat(1);
                break;
        }
    }

    private int npcAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(NPCEntity n : npcHandler.getEntities()) {
            if(newX > (n.getX() - 96) && newX < n.getX() + n.getWidth()
                    && newY > (n.getY() - 96) && newY < n.getY() + n.getHeight()) {
                return n.getId();
            }
        }
        return -1;
    }

    private int objectAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX > (o.getX() - 96) && newX < o.getX() + o.getWidth()
                    && newY > (o.getY() - 96) && newY < o.getY() + o.getHeight()) {
                return o.getId();
            }
        }
        return -1;
    }

    private int npcIdAt(int x_, int y_) {
        int newX = player.getX() + x_;
        int newY = player.getY() + y_;
        for(NPCEntity n : npcHandler.getEntities()) {
            if(newX >= n.getX() && newX <= n.getX() + n.getWidth() - 96) {
                if(newY >= n.getY() && newY <= n.getY() + n.getHeight() - 96) {
                    return n.getId();
                }
            }
        }
        return -1;
    }

    private int objectIdAt(int x_, int y_) {
        int newX = player.getX() + (x_ * 96);
        int newY = player.getY() + (y_ * 96);
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX >= o.getX() && newX <= o.getX() + o.getWidth() - 96) {
                if(newY >= o.getY() && newY <= o.getY() + o.getHeight() - 96) {
                    return o.getId();
                }
            }
        }
        return -1;
    }
    //endregion
}
