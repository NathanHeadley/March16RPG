package com.zelphinstudios.march16rpg.threads;

import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.instances.Player;

public class BaseThread implements Runnable {

    //region Variables
    protected Thread thread = null;
    protected boolean running = false;
    protected Player player;
    protected NPCHandler npcHandler;
    //endregion

    //region Constructor
    public BaseThread(Player player_, NPCHandler npcHandler_) {
        player = player_;
        npcHandler = npcHandler_;
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
