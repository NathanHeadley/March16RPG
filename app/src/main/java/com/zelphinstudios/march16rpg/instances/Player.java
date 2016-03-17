package com.zelphinstudios.march16rpg.instances;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.R;
import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;
import com.zelphinstudios.march16rpg.util.FileLoader;

import java.io.File;
import java.util.Vector;

public class Player extends BaseInstance {

    //region Variables
    private Context context;

    private int direction,
            meleePower,
            rangePower,
            inventory[],
            inventoryQuantity[],
            quests[],
            levels[],
            xp[];

    private float x,
            y,
            meleeSpeed,
            rangeSpeed,
            defence,
            healthMax,
            healthCurrent;

    private String images[] = new String[4]; // Used for saving bitmaps

    // Temporary Variables - do not save
    private BitmapDecoder bitmapDecoder;
    private NPCEntity target = null;
    private boolean inCombat = false;
    private long lastAttack = 0;
    private int numberQuests = 0;
    //endregion

    //region Constructor
    public Player(Context context_) {
        // Grab integer values
        inventory = new int[context_.getResources().getInteger(R.integer.inventSize)];
        inventoryQuantity = new int[context_.getResources().getInteger(R.integer.inventSize)];
        quests = new int[context_.getResources().getInteger(R.integer.numberQuests)];
        levels = new int[context_.getResources().getInteger(R.integer.skills)];
        xp = new int[context_.getResources().getInteger(R.integer.skills)];
        numberQuests = context_.getResources().getInteger(R.integer.numberQuests);

        context = context_;
        bitmapDecoder = new BitmapDecoder(context_);
        File file = context_.getApplicationContext().getFileStreamPath("player.txt");
        if(file.exists()) {
            Log.e("Nathan", "Attempting to load player file..");
            //Log.e("Nathan", "Deleted? = " + file.delete());
            //newPlayer();
            loadGame();
            Log.e("Nathan", "Loaded player file..!");
        } else {
            Log.e("Nathan", "Creating player file..");
            newPlayer();
            loadGame();
        }
    }
    //endregion

    //region Methods
    private void loadGame() {
        FileLoader fileLoader = new FileLoader(context);
        Vector<Item> itemList = fileLoader.loadItemList();
        for(Item i : itemList) {
            Log.e("Nathan", "----------------");
            Log.e("Nathan", " ");
            Log.e("Nathan", "ITEM: " + i.getId());
            Log.e("Nathan", "name = " + i.getName());
            Log.e("Nathan", "value = " + i.getValue());
            Log.e("Nathan", "mPower = " + i.getMeleePower());
            Log.e("Nathan", "rPower = " + i.getRangePower());
            Log.e("Nathan", "defence = " + i.getDefence());
            Log.e("Nathan", "equip = " + i.getEquipSlot());
            Log.e("Nathan", "stack = " + i.getStackable());
            Log.e("Nathan", "bitmap = " + i.getBitmap(0));
            Log.e("Nathan", "bitmap = " + i.getBitmap(1));
            Log.e("Nathan", "bitmap = " + i.getBitmap(2));
            Log.e("Nathan", "bitmap = " + i.getBitmap(3));
            Log.e("Nathan", " ");
            Log.e("Nathan", "----------------");
        }
        Vector<NPC> npcList = fileLoader.loadNPCList();
        for(NPC n : npcList) {
            Log.e("Nathan", "----------------");
            Log.e("Nathan", " ");
            Log.e("Nathan", "NPC: " + n.getId());
            Log.e("Nathan", "name = " + n.getName());
            Log.e("Nathan", "chat = " + n.getChat());
            Log.e("Nathan", "level = " + n.getLevel());
            Log.e("Nathan", "attack = " + n.getAttack());
            Log.e("Nathan", "defence = " + n.getDefence());
            Log.e("Nathan", "speed = " + n.getSpeed());
            Log.e("Nathan", "healthmax = " + n.getHealthMax());
            Log.e("Nathan", "attackRange = " + n.getAttackRange());
            Log.e("Nathan", "walkRange = " + n.getWalkRange());
            Log.e("Nathan", "bitmap = " + n.getBitmap(0));
            Log.e("Nathan", "bitmap = " + n.getBitmap(1));
            Log.e("Nathan", "bitmap = " + n.getBitmap(2));
            Log.e("Nathan", "bitmap = " + n.getBitmap(3));
            Log.e("Nathan", " ");
            Log.e("Nathan", "----------------");
        }
        Vector<Object> objectList = fileLoader.loadObjectList();
        for(Object o : objectList) {
            Log.e("Nathan", "----------------");
            Log.e("Nathan", " ");
            Log.e("Nathan", "OBJECT: " + o.getId());
            Log.e("Nathan", "name = " + o.getName());
            Log.e("Nathan", "width = " + o.getWidth());
            Log.e("Nathan", "height = " + o.getHeight());
            Log.e("Nathan", "action = " + o.getAction());
            Log.e("Nathan", "walkable = " + o.isWalkable());
            Log.e("Nathan", "bitmap = " + o.getBitmap(0));
            Log.e("Nathan", "bitmap = " + o.getBitmap(1));
            Log.e("Nathan", "bitmap = " + o.getBitmap(2));
            Log.e("Nathan", "bitmap = " + o.getBitmap(3));
            Log.e("Nathan", " ");
            Log.e("Nathan", "----------------");
        }
        Vector<Terrain> terrainList = fileLoader.loadTerrainList();
        for(Terrain t : terrainList) {
            Log.e("Nathan", "----------------");
            Log.e("Nathan", " ");
            Log.e("Nathan", "TERRAIN: " + t.getId());
            Log.e("Nathan", "name = " + t.getName());
            Log.e("Nathan", "visible = " + t.isVisible());
            Log.e("Nathan", "walkable = " + t.isWalkable());
            Log.e("Nathan", "bitmap = " + t.getBitmap(0));
            Log.e("Nathan", " ");
            Log.e("Nathan", "----------------");
        }
    }

    private void newPlayer() {
        name = "";
        x = 0f;
        y = 0f;
        direction = 2;
        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = 0;
            inventoryQuantity[i] = 0;
        }
        for (int q = 0; q < numberQuests; q++) {
            quests[q] = 0;
        }
        for(int l = 0; l < levels.length; l++) {
            levels[l] = 0;
            xp[l] = 0;
        }
        meleePower = 0; meleeSpeed = 0f;
        rangePower = 0; rangeSpeed = 0f;
        healthMax = 0f; healthCurrent = 0f;
        defence = 0f;
        images[0] = "player_north";
        images[1] = "player_east";
        images[2] = "player_south";
        images[3] = "player_west";
        for(int b = 0; b < 4; b++) {
            bitmap[b] = bitmapDecoder.decode(images[b]);
        }
        calculateStats();
    }

    public void calculateStats() {
        meleePower = 5;
        if(levels[0] > 1) {
            for (int i = 2; i < levels[0] + 1; i++) {
                meleePower += Math.floor((i-1) / 5) + 2; // TODO: new melee formula?
            }
        }
        meleeSpeed = 1.5f;
        meleeSpeed -= (levels[1] - 1) * 0.02;

        healthMax = 50;
        if(levels[4] > 1) {
            for (int i = 2; i < levels[4] + 1; i++) {
                healthMax += (i - 1) * 10;
            }
        }
        healthCurrent = healthMax; // TODO: Remove me - infinite health

        defence = (float)levels[5] * 0.5f;
    }


    //endregion

    //region Getters
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getDirection() {
        return direction;
    }
    public int getMeleePower() {
        return meleePower;
    }
    public int getRangePower() {
        return rangePower;
    }
    public float getMeleeSpeed() {
        return meleeSpeed;
    }
    public float getRangeSpeed() {
        return rangeSpeed;
    }
    public float getDefence() {
        return defence;
    }
    public float getHealthMax() {
        return healthMax;
    }
    public float getHealthCurrent() {
        return healthCurrent;
    }
    public int[] getInventory() {
        return inventory;
    }
    public int getItem(int item_) {
        return inventory[item_];
    }
    public int[] getInventoryQuantity() {
        return inventoryQuantity;
    }
    public int getItemQuantity(int quantity_) {
        return inventoryQuantity[quantity_];
    }
    public int[] getQuests() {
        return quests;
    }
    public int getQuest(int quest_) {
        return quests[quest_];
    }
    public int[] getLevels() {
        return levels;
    }
    public int getLevel(int level_) {
        return levels[level_];
    }
    public int[] getXPs() {
        return xp;
    }
    public int getXP(int xp_) {
        return xp[xp_];
    }
    public String[] getImages() {
        return images;
    }
    public String getImage(int image_) {
        return images[image_];
    }
    public NPCEntity getTarget() {
        return target;
    }
    public long getLastAttack() {
        return lastAttack;
    }
    public boolean isInCombat() {
        return inCombat;
    }
    //endregion

    //region Setters
    public void setX(float x_) {
        x = x_;
    }
    public void setY(float y_) {
        y = y_;
    }
    public void setDirection(int direction_) {
        direction = direction_;
    }
    public void setMeleePower(int meleePower_) {
        meleePower = meleePower_;
    }
    public void setRangePower(int rangePower_) {
        rangePower = rangePower_;
    }
    public void setMeleeSpeed(float meleeSpeed_) {
        meleeSpeed = meleeSpeed_;
    }
    public void setRangeSpeed(float rangeSpeed_) {
        rangeSpeed = rangeSpeed_;
    }
    public void setDefence(float defence_) {
        defence = defence_;
    }
    public void setHealthMax(float healthMax_) {
        healthMax = healthMax_;
    }
    public void setHealthCurrent(float healthCurrent_) {
        healthCurrent = healthCurrent_;
    }
    public void setInventoryItem(int slot_, int id_, int quantity_) {
        inventory[slot_] = id_;
        inventoryQuantity[slot_] = quantity_;
    }
    public void setQuest(int quest_, int progression_) {
        quests[quest_] = progression_;
    }
    public void setLevel(int skill_, int level_) {
        levels[skill_] = level_;
    }
    public void setXP(int skill_, int xp_) {
        xp[skill_] = xp_;
    }
    public void setImage(int id_, String image_) {
        images[id_] = image_;
    }
    public void setTarget(NPCEntity npc_) {
        target = npc_;
    }
    public void setLastAttack(long attack_) {
        lastAttack = attack_;
    }
    public void setInCombat(boolean combat_) {
        inCombat = combat_;
    }
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
}
