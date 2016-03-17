package com.zelphinstudios.march16rpg.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.zelphinstudios.march16rpg.handlers.ActionHandler;
import com.zelphinstudios.march16rpg.handlers.InterfaceHandler;
import com.zelphinstudios.march16rpg.handlers.ItemHandler;
import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.handlers.ObjectHandler;
import com.zelphinstudios.march16rpg.handlers.TerrainHandler;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.instances.gui.Button;
import com.zelphinstudios.march16rpg.instances.gui.GUI;
import com.zelphinstudios.march16rpg.threads.NPCThread;
import com.zelphinstudios.march16rpg.threads.PlayerThread;
import com.zelphinstudios.march16rpg.views.GameView;

public class GameActivity extends Activity implements View.OnTouchListener {

    //region Variables
    Player player;
    ObjectHandler objectHandler;
    NPCHandler npcHandler;
    TerrainHandler terrainHandler;
    InterfaceHandler interfaceHandler;
    ItemHandler itemHandler;
    ActionHandler actionHandler;
    NPCThread npcThread;
    PlayerThread playerThread;
    GameView gameView;
    //endregion

    //region Methods
    @Override
    protected void onCreate(Bundle savedInstanceState_) {
        super.onCreate(savedInstanceState_);

        //region Initiate Instances
        player = new Player(this);
        objectHandler = new ObjectHandler(this);
        npcHandler = new NPCHandler(this);
        terrainHandler = new TerrainHandler(this);
        interfaceHandler = new InterfaceHandler(this, player);
        itemHandler = new ItemHandler(this, player);
        actionHandler = new ActionHandler(this, player, interfaceHandler, objectHandler, npcHandler, terrainHandler, itemHandler);
        npcThread = new NPCThread(player, npcHandler);
        playerThread = new PlayerThread(player, npcHandler);
        gameView = new GameView(this, player, interfaceHandler, objectHandler, npcHandler, terrainHandler);
        gameView.setOnTouchListener(this);
        //endregion

        setContentView(gameView);
    }

    @Override
    public boolean onTouch(View view_, MotionEvent motionEvent_) {
        if(motionEvent_.getAction() == MotionEvent.ACTION_DOWN) {
            float x = motionEvent_.getX();
            float y = motionEvent_.getY();
            for(GUI gui : interfaceHandler.getGUIs()) {
                for (Button button : gui.getButtons()) {
                    // TODO: Add button widths and heights (144 is custom)
                    if(x >= button.getX() && x <= (button.getX() + 144)
                            && y >= button.getY() && y <= (button.getY() + 144)) {
                        actionHandler.action(button.getAction());
                        button.setState(1);
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.onPause();
        npcThread.onPause();
        playerThread.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
        npcThread.onResume();
        playerThread.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < 19) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN);
            } else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
    //endregion
}
