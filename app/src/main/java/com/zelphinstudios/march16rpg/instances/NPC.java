package com.zelphinstudios.march16rpg.instances;

import android.graphics.Bitmap;

public class NPC extends BaseInstance {

    //region Variables
    private int chat,
            level,
            attack,
            attackRange,
            walkRange;

    private float healthMax,
            defence,
            speed;
    //endregion

    //region Constructor
    public NPC() {
        id = -1;
        name = "null";
        chat = -1;
        level = -1;
        attack = 0;
        defence = 0;
        speed = 0;
        healthMax = 0;
        attackRange = 0;
        walkRange = 0;
        bitmap[0] = null;
        bitmap[1] = null;
        bitmap[2] = null;
        bitmap[3] = null;
    }
    //endregion

    //region Methods
    //endregion

    //region Getters
    public int getChat() {
        return chat;
    }
    public int getLevel() {
        return level;
    }
    public int getAttack() {
        return attack;
    }
    public float getDefence() {
        return defence;
    }
    public float getSpeed() {
        return speed;
    }
    public float getHealthMax() {
        return healthMax;
    }
    public int getAttackRange() {
        return attackRange;
    }
    public int getWalkRange() {
        return walkRange;
    }
    //endregion

    //region Setters
    public void setChat(int chat_) {
        chat = chat_;
    }
    public void setLevel(int level_) {
        level = level_;
    }
    public void setAttack(int attack_) {
        attack = attack_;
    }
    public void setDefence(float defence_) {
        defence = defence_;
    }
    public void setSpeed(float speed_) {
        speed = speed_;
    }
    public void setHealthMax(float healthMax_) {
        healthMax = healthMax_;
    }
    public void setAttackRange(int attackRange_) {
        attackRange = attackRange_;
    }
    public void setWalkRange(int walkRange_) {
        walkRange = walkRange_;
    }
    //endregion
}
