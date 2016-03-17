package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;

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
            // TODO: Add buttons here
            case 100: // A button
                break;
        }
    }
    //endregion
}
