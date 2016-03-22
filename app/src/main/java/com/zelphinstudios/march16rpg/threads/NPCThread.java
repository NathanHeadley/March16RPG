package com.zelphinstudios.march16rpg.threads;

import android.os.SystemClock;
import android.util.Log;

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

    //region Variables
    private int interval = 100;
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
                                if (SystemClock.elapsedRealtime() - (entity.getSpeed() * 1000) > entity.getLastAttack()) {
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
                    double walk = Math.random() - Math.random();
                    if(walk > 0) {
                        int direction = (int)(Math.random() * 4);
                        switch(direction) {
                            case 0: //north
                                if(objectAt(entity, 0, -1) == -1) {
                                    entity.changeY(-1);
                                    interval = 500;
                                }
                                break;
                            case 1: //east
                                if(objectAt(entity, 1, 0) == -1) {
                                    entity.changeX(1);
                                    interval = 500;
                                }
                                break;
                            case 2: //south
                                if(objectAt(entity, 0, 1) == -1) {
                                    entity.changeY(1);
                                    interval = 500;
                                }
                                break;
                            case 3: //west
                                if(objectAt(entity, -1, 0) == -1) {
                                    entity.changeX(-1);
                                    interval = 500;
                                }
                                break;
                        }
                    }
                }
            }
            try {
                Thread.sleep(interval, 0);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private float getDistance(NPCEntity entity_) {
        return (float)Math.sqrt(Math.pow((player.getX() - entity_.getX()), 2)
                + Math.pow((player.getY() - entity_.getY()), 2));
    }

    private int objectAt(NPCEntity entity_, int x_, int y_) {
        int newX = entity_.getX() + x_;
        int newY = entity_.getY() + y_;
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX > (o.getX() - 1) && newX < (o.getX() + o.getWidth()) && newY > (o.getY() - 1) && newY < (o.getY() + o.getHeight())) {
                return o.getId();
            }
        }
        return -1;
    }
    //endregion
}
