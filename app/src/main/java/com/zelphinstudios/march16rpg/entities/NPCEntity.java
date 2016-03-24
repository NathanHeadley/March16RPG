package com.zelphinstudios.march16rpg.entities;

import com.zelphinstudios.march16rpg.instances.NPC;

public class NPCEntity extends BaseEntity {

    //region Variables
    private int spawnX,
            spawnY,
            level,
            attack,
            attackRange,
            walkRange;

    private float healthMax,
            healthCurrent,
            defence,
            speed;

    private boolean inCombat;

    private long lastAttack;
    //endregion

    //region Constructor
    public NPCEntity(NPC npc_, int x_, int y_) {
        id = npc_.getId();
        x = x_*96;
        y = y_*96;
        spawnX = x;
        spawnY = y;
        level = npc_.getLevel();
        attack = npc_.getAttack();
        attackRange = npc_.getAttackRange();
        walkRange = npc_.getWalkRange();
        healthMax = npc_.getHealthMax();
        defence = npc_.getDefence();
        speed = npc_.getSpeed();
        healthCurrent = healthMax;
        inCombat = false;
        lastAttack = 0;
        visible = true;
    }
    //endregion

    //region Methods
    public void changeHealth(float health_) {
        if(healthCurrent + health_ > healthMax) {
            healthCurrent = healthMax;
        } else if(healthCurrent + health_ <= 0) {
            healthCurrent = 0;
        } else {
            healthCurrent += health_;
        }
    }
    //endregion

    //region Getters
    public int getSpawnX() {
        return spawnX;
    }
    public int getSpawnY() {
        return spawnY;
    }
    public int getLevel() {
        return level;
    }
    public int getAttack() {
        return attack;
    }
    public int getAttackRange() {
        return attackRange;
    }
    public int getWalkRange() {
        return walkRange;
    }
    public float getHealthMax() {
        return healthMax;
    }
    public float getHealthCurrent() {
        return healthCurrent;
    }
    public float getDefence() {
        return defence;
    }
    public float getSpeed() {
        return speed;
    }
    public boolean isInCombat() {
        return inCombat;
    }
    public long getLastAttack() {
        return lastAttack;
    }
    //endregion

    //region Setters
    public void setSpawnX(int x_) {
        spawnX = x_;
    }
    public void setSpawnY(int y_) {
        spawnY = y_;
    }
    public void setLevel(int level_) {
        level = level_;
    }
    public void setAttack(int attack_) {
        attack = attack_;
    }
    public void setAttackRange(int attackRange_) {
        attackRange = attackRange_;
    }
    public void setWalkRange(int walkRange_) {
        walkRange = walkRange_;
    }
    public void setHealthMax(float healthMax_) {
        healthMax = healthMax_;
    }
    public void setHealthCurrent(float healthCurrent_) {
        healthCurrent = healthCurrent_;
    }
    public void setDefence(float defence_) {
        defence = defence_;
    }
    public void setSpeed(float speed_) {
        speed = speed_;
    }
    public void setInCombat(boolean inCombat_) {
        inCombat = inCombat_;
    }
    public void setLastAttack(long lastAttack_) {
        lastAttack = lastAttack_;
    }
    //endregion
}
