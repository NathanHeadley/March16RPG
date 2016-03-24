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
                        walk(entity); //TODO: force direction of target
                    }
                } else {
                    walk(entity);
                }
            }
            try {
                Thread.sleep(interval, 0);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private void walk(NPCEntity entity_) {
        double walk = Math.random() - Math.random();
        if(walk > 0) {
            int direction = (int)(Math.random() * 4);
            switch(direction) {
                case 0: //north
                    if(objectAt(entity_, 0, -1) == -1) {
                        entity_.changeY(-1);
                        interval = 500;
                    }
                    break;
                case 1: //east
                    if(objectAt(entity_, 1, 0) == -1) {
                        entity_.changeX(1);
                        interval = 500;
                    }
                    break;
                case 2: //south
                    if(objectAt(entity_, 0, 1) == -1) {
                        entity_.changeY(1);
                        interval = 500;
                    }
                    break;
                case 3: //west
                    if(objectAt(entity_, -1, 0) == -1) {
                        entity_.changeX(-1);
                        interval = 500;
                    }
                    break;
            }
        }
    }

    private float getDistance(NPCEntity entity_) {
        return (float)Math.sqrt(Math.pow((player.getX() - entity_.getX()), 2)
                + Math.pow((player.getY() - entity_.getY()), 2));
    }

    private int objectAt(NPCEntity entity_, int x_, int y_) {
        int newX = entity_.getAbsX() + x_;
        int newY = entity_.getAbsY() + y_;
        if((newX < (entity_.getSpawnX() - entity_.getWalkRange())) || (newX > (entity_.getSpawnX() + entity_.getWalkRange()))) {
            return -2;
        }
        if((newY < (entity_.getSpawnY() - entity_.getWalkRange())) || (newY > (entity_.getSpawnY() + entity_.getWalkRange()))) {
            return -2;
        }
        for(ObjectEntity o : objectHandler.getEntities()) {
            if(newX > (o.getAbsX() - 96) && newX < (o.getAbsX() + o.getAbsWidth())
                    && newY > (o.getAbsY() - 96) && newY < (o.getAbsY() + o.getAbsHeight())) {
                return o.getId();
            }
        }
        return -1;
    }
    //endregion
}
