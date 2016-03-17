package com.zelphinstudios.march16rpg.threads;

import android.os.SystemClock;

import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.instances.Player;

public class NPCThread extends BaseThread {

    //region Constructor
    public NPCThread(Player player_, NPCHandler npcHandler_) {
        super(player_, npcHandler_);
    }
    //endregion

    //region Methods
    @Override
    public void run() {
        while (running) {
            for (NPCEntity entity : npcHandler.getEntities()) {
                if (entity.isInCombat()) {
                    if (getDistance(entity) <= entity.getAttackRange()) {
                        if (entity.getHealthCurrent() > 0) {
                            if (player.getHealthCurrent() != 0) {
                                if (SystemClock.elapsedRealtime() - (entity.getSpeed() * 1000) >
                                        entity.getLastAttack()) {
                                    float damage = (entity.getAttack() / 100.0f) * (100.0f - player.getDefence());
                                    player.changeHealth((float) -Math.ceil(damage));
                                    entity.setLastAttack(SystemClock.elapsedRealtime());
                                }
                            } else {
                                entity.setInCombat(false);
                                player.setInCombat(false);
                                // TODO: Player Death
                            }
                        } else {
                            entity.setInCombat(false);
                            player.setInCombat(false);
                            // TODO: NPC Death
                        }
                    } else {
                        // TODO: NPC Walking
                    }
                } else {
                    // TODO: NPC Walking (Implemented with attack range line)
                }
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private float getDistance(NPCEntity entity_) {
        return (float)Math.sqrt(Math.pow((player.getX() - entity_.getX()), 2)
                + Math.pow((player.getY() - entity_.getY()), 2));
    }
    //endregion
}
