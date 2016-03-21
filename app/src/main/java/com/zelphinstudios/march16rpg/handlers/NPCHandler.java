package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;

import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.instances.NPC;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

import java.util.Vector;

public class NPCHandler extends BaseHandler {

    //region Variables
    private Vector<NPC> npcs = new Vector<>();
    private Vector<NPCEntity> entities = new Vector<>();
    //endregion

    //region Constructor
    public NPCHandler(Context context_) {
        bitmapDecoder = new BitmapDecoder(context_);
        fileLoader = new FileLoader(context_);

        // Loading NPCs
        npcs = fileLoader.loadNPCList();

        // Entities - NPC, X, Y

        //region Entity List
        entities.addElement(new NPCEntity(npcs.get(1), 4, 4));
        //endregion
    }
    //endregion

    //region Getters
    public Vector<NPC> getNpcs() {
        return npcs;
    }
    public Vector<NPCEntity> getEntities() {
        return entities;
    }
    //endregion
}
