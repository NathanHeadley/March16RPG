package com.zelphinstudios.march16rpg.threads;

import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.handlers.ObjectHandler;
import com.zelphinstudios.march16rpg.instances.Player;

public class BaseThread implements Runnable {

    //region Variables
    protected Thread thread = null;
    protected boolean running = false;
    protected Player player;
    protected NPCHandler npcHandler;
    protected ObjectHandler objectHandler;
    //endregion

    //region Constructor
    public BaseThread(Player player_, NPCHandler npcHandler_, ObjectHandler objectHandler_) {
        player = player_;
        npcHandler = npcHandler_;
        objectHandler = objectHandler_;
    }
    //endregion

    //region Methods
    public void run() {} // Override this
    public void onPause() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread = null;
    }
    public void onResume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    //endregion
}
