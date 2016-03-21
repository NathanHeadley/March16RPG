package com.zelphinstudios.march16rpg.threads;

import android.os.SystemClock;

import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.entities.ObjectEntity;
import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.handlers.ObjectHandler;
import com.zelphinstudios.march16rpg.instances.Player;

public class NPCThread extends BaseThread {

    //region Constructor
    public NPCThread(Player player_, NPCHandler npcHandler_, ObjectHandler objectHandler_) {
        super(player_, npcHandler_, objectHandler_);
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
                    double walk = Math.random() - Math.random();
                    if(walk > 0) {
                        int direction = (int)(Math.random() * 4);
                        switch(direction) {
                            case 0: //north
                                /*int newY = entity.getY() - 96;
                                for(ObjectEntity object : objectHandler.getEntities()) {
                                    if(object.getY() == newY) {
                                        break;
                                    }
                                }
                                entity.setY(entity.getY() + 96);*/
                                break;
                            case 1: //east
                                break;
                            case 2: //south
                                break;
                            case 3: //west
                                break;
                        }
                    }
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
