package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;

import com.zelphinstudios.march16rpg.entities.TerrainEntity;
import com.zelphinstudios.march16rpg.instances.Terrain;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

import java.util.Vector;

public class TerrainHandler extends BaseHandler {

    //region Variables
    private Vector<Terrain> terrains = new Vector<>();
    private Vector<TerrainEntity> entities = new Vector<>();
    //endregion

    //region Constructor
    public TerrainHandler(Context context_) {
        bitmapDecoder = new BitmapDecoder(context_);
        fileLoader = new FileLoader(context_);

        // Loading Terrains
        terrains = fileLoader.loadTerrainList();

        // Entities - Id, X, Y

        //region Entity List
        entities.addElement(new TerrainEntity(0, 0, 0));
        //endregion
    }
    //endregion

    //region Getters
    public Vector<Terrain> getTerrains() {
        return terrains;
    }
    public Vector<TerrainEntity> getEntities() {
        return entities;
    }
    //endregion
}
