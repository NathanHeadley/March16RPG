package com.zelphinstudios.march16rpg.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapDecoder {

    //region Variables
    private Context context;
    //endregion

    //region Constructor
    public BitmapDecoder(Context context_) {
        context = context_;
    }
    //endregion

    //region Methods
    public Bitmap decode(String bitmap_) {
        return BitmapFactory.decodeResource(context.getResources(),
                context.getResources().getIdentifier(bitmap_, "drawable", context.getPackageName()));
    }
    //endregion

    //region Getters
    //endregion

    //region Setters
    //endregion
}

//region Variables
//endregion

//region Constructor
//endregion

//region Methods
//endregion

//region Getters
//endregion

//region Setters
//endregion