package com.zelphinstudios.march16rpg.threads;

import android.os.SystemClock;

import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.instances.Player;

public class PlayerThread extends BaseThread {

    //region Constructor
    public PlayerThread(Player player_, NPCHandler npcHandler_) {
        super(player_, npcHandler_);
    }
    //endregion

    //region Methods
    @Override
    public void run() {
        while(running) {
            if(player.isInCombat()) {
                if(getDistance() <= 96) {
                    if(player.getHealthCurrent() != 0) {
                        if(player.getTarget().getHealthCurrent() > 0) {
                            if(SystemClock.elapsedRealtime() - (player.getMeleeSpeed() * 1000)
                                    > player.getLastAttack()) {
                                float damage = (player.getMeleePower() / 100.0f) * (100.0f - player.getTarget().getDefence());
                                player.getTarget().changeHealth((float)-Math.ceil(damage));
                                player.setLastAttack(SystemClock.elapsedRealtime());
                            }
                        } else {
                            player.setInCombat(false);
                            player.getTarget().setInCombat(false);
                            // TODO: NPC Death
                        }
                    } else {
                        player.setInCombat(false);
                        player.getTarget().setInCombat(false);
                        // TODO: Player Death
                    }
                }
            }
        }
    }

    private float getDistance() {
        return (float)Math.sqrt(Math.pow((player.getX() - player.getTarget().getX()), 2)
                + Math.pow((player.getY() - player.getTarget().getY()), 2));
    }
    //endregion
}
