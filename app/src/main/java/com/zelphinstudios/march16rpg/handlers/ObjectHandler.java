package com.zelphinstudios.march16rpg.handlers;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.entities.ObjectEntity;
import com.zelphinstudios.march16rpg.instances.Object;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

import java.util.Vector;

public class ObjectHandler extends BaseHandler {

    //region Variables
    private Vector<Object> objects = new Vector<>();
    private Vector<ObjectEntity> entities = new Vector<>();
    //endregion

    //region Constructor
    public ObjectHandler(Context context_) {
        bitmapDecoder = new BitmapDecoder(context_);
        fileLoader = new FileLoader(context_);

        // Loading objects
        objects = fileLoader.loadObjectList();
        // Entities - Id, X, Y

        //region Entity List
        entities.addElement(new ObjectEntity(objects.get(0), 1, 1));
        //endregion
    }
    //endregion

    //region Getters
    public Vector<Object> getObjects() {
        return objects;
    }
    public Vector<ObjectEntity> getEntities() {
        return entities;
    }
    //endregion
}
