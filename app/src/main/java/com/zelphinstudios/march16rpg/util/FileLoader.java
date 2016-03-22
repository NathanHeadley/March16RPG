package com.zelphinstudios.march16rpg.util;

import android.content.Context;
import android.util.Log;

import com.zelphinstudios.march16rpg.entities.ItemEntity;
import com.zelphinstudios.march16rpg.instances.Item;
import com.zelphinstudios.march16rpg.instances.NPC;
import com.zelphinstudios.march16rpg.instances.Object;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.instances.Terrain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class FileLoader {

    private Context context;
    private BitmapDecoder bitmapDecoder;
    private Vector<Item> itemList = new Vector<>();
    private Vector<NPC> npcList = new Vector<>();
    private Vector<Object> objectList = new Vector<>();
    private Vector<Terrain> terrainList = new Vector<>();

    public FileLoader(Context context_) {
        context = context_;
        bitmapDecoder = new BitmapDecoder(context);
    }

    public void savePlayerFile(Player player_) {
        String toSave = "[player=" + player_.getName() + "|x=" + player_.getX() + "|y=" + player_.getY() + "|health="+player_.getHealthCurrent();
        int count = 0;
        for(ItemEntity itemEntity : player_.getInventory()) {
            toSave += "|inventory=" + count + "=" + itemEntity.getId() + "=" + itemEntity.getQuantity();
            count++;
        }
        count = 0;
        for(int quest : player_.getQuests()) {
            toSave += "|quest=" + count + "=" + quest;
            count++;
        }
        count = 0;
        for(int xp : player_.getXPs()) {
            toSave += "|xp=" + count + "=" + xp;
            count++;
        }
        toSave += "]";
        Log.e("Nathan", toSave);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(context.getFilesDir() + "player.txt")));
            writer.write(toSave);
            writer.close();
        } catch (Exception io) {
            Log.e("Nathan", io.toString());
        }
    }

    public Player loadPlayerFile() {
        // Future Updates; multiple players, load different looks
        Player p = new Player(context);
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(context.getFilesDir() + "player.txt")));
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput("player")));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.substring(0, 1).equals("[")) {
                    readLine = readLine.substring(1, readLine.length() - 1);
                    String[] subSections = readLine.split("\\|");
                    for(String s : subSections) {
                        String[] subLine = s.split("=");
                        switch(subLine[0]) {
                            case "player":
                                p.setName(subLine[1]);
                                break;
                            case "x":
                                p.setX(Integer.parseInt(subLine[1]));
                                break;
                            case "y":
                                p.setY(Integer.parseInt(subLine[1]));
                                break;
                            case "health":
                                p.setHealthCurrent(Float.parseFloat(subLine[1]));
                                break;
                            case "inventory":
                                p.setInventoryItem(Integer.parseInt(subLine[1]), Integer.parseInt(subLine[2]), Integer.parseInt(subLine[3]));
                                break;
                            case "quest":
                                p.setQuest(Integer.parseInt(subLine[1]), Integer.parseInt(subLine[2]));
                                break;
                            case "xp":
                                p.setXP(Integer.parseInt(subLine[1]), Integer.parseInt(subLine[2]));
                                break;
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException io) {
            Log.e("Nathan", "Player Loading: " + io.toString());
        }
        return p;
    }

    public Vector<Item> loadItemList() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("itemlist")));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.substring(0, 1).equals("[")) {
                    readLine = readLine.substring(1, readLine.length() - 1);
                    Item item = new Item();
                    String[] subSections = readLine.split("\\|");
                    for(String s : subSections) {
                        String[] subLine = s.split("=");
                        switch(subLine[0]) {
                            case "id":
                                item.setId(Integer.parseInt(subLine[1]));
                                break;
                            case "name":
                                item.setName(subLine[1]);
                                break;
                            case "value":
                                item.setValue(Integer.parseInt(subLine[1]));
                                break;
                            case "meleepower":
                                item.setMeleePower(Integer.parseInt(subLine[1]));
                                break;
                            case "rangepower":
                                item.setRangePower(Integer.parseInt(subLine[1]));
                                break;
                            case "defence":
                                item.setDefence(Integer.parseInt(subLine[1]));
                                break;
                            case "equipslot":
                                item.setEquipSlot(Integer.parseInt(subLine[1]));
                                break;
                            case "stackable":
                                if(subLine[1].equals("true")) {
                                    item.setStackable(true);
                                } else {
                                    item.setStackable(false);
                                }
                                break;
                            case "bitmap":
                                item.setBitmap(0, bitmapDecoder.decode(subLine[1]));
                                item.setBitmap(1, item.getBitmap(0));
                                item.setBitmap(2, item.getBitmap(0));
                                item.setBitmap(3, item.getBitmap(0));
                                break;
                            case "bitmap1":
                                item.setBitmap(1, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap2":
                                item.setBitmap(2, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap3":
                                item.setBitmap(3, bitmapDecoder.decode(subLine[1]));
                                break;
                        }
                    }
                    itemList.addElement(item);
                }

            }
            bufferedReader.close();
        } catch (IOException io) {
            Log.e("Nathan", "Item Loading: "+io.toString());
        }
        return itemList;
    }

    public Vector<NPC> loadNPCList() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("npclist")));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.substring(0, 1).equals("[")) {
                    readLine = readLine.substring(1, readLine.length() - 1);
                    NPC npc = new NPC();
                    String[] subSections = readLine.split("\\|");
                    for(String s : subSections) {
                        String[] subLine = s.split("=");
                        switch(subLine[0]) {
                            case "id":
                                npc.setId(Integer.parseInt(subLine[1]));
                                break;
                            case "name":
                                npc.setName(subLine[1]);
                                break;
                            case "chat":
                                npc.setChat(Integer.parseInt(subLine[1]));
                                break;
                            case "level":
                                npc.setLevel(Integer.parseInt(subLine[1]));
                                break;
                            case "attack":
                                npc.setAttack(Integer.parseInt(subLine[1]));
                                break;
                            case "defence":
                                npc.setDefence(Float.parseFloat(subLine[1]));
                                break;
                            case "speed":
                                npc.setSpeed(Float.parseFloat(subLine[1]));
                                break;
                            case "healthmax":
                                npc.setHealthMax(Integer.parseInt(subLine[1]));
                                break;
                            case "attackrange":
                                npc.setAttackRange(Integer.parseInt(subLine[1]));
                                break;
                            case "walkrange":
                                npc.setWalkRange(Integer.parseInt(subLine[1]));
                                break;
                            case "bitmap":
                                npc.setBitmap(0, bitmapDecoder.decode(subLine[1]));
                                npc.setBitmap(1, npc.getBitmap(0));
                                npc.setBitmap(2, npc.getBitmap(0));
                                npc.setBitmap(3, npc.getBitmap(0));
                                break;
                            case "bitmap1":
                                npc.setBitmap(1, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap2":
                                npc.setBitmap(2, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap3":
                                npc.setBitmap(3, bitmapDecoder.decode(subLine[1]));
                                break;
                        }
                    }
                    npcList.addElement(npc);
                }
            }
            bufferedReader.close();
        } catch (IOException io) {
            Log.e("Nathan", "NPC Loading: "+io.toString());
        }
        return npcList;
    }

    public Vector<Object> loadObjectList() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("objectlist")));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.substring(0, 1).equals("[")) {
                    readLine = readLine.substring(1, readLine.length() - 1);
                    Object object = new Object();
                    String[] subSections = readLine.split("\\|");
                    for(String s : subSections) {
                        String[] subLine = s.split("=");
                        switch(subLine[0]) {
                            case "id":
                                object.setId(Integer.parseInt(subLine[1]));
                                break;
                            case "name":
                                object.setName(subLine[1]);
                                break;
                            case "width":
                                object.setWidth(Integer.parseInt(subLine[1]));
                                break;
                            case "height":
                                object.setHeight(Integer.parseInt(subLine[1]));
                                break;
                            case "action":
                                object.setAction(Integer.parseInt(subLine[1]));
                                break;
                            case "walkable":
                                if(subLine[1].equals("true")) {
                                    object.setWalkable(true);
                                } else {
                                    object.setWalkable(false);
                                }
                                break;
                            case "bitmap":
                                object.setBitmap(0, bitmapDecoder.decode(subLine[1]));
                                object.setBitmap(1, object.getBitmap(0));
                                object.setBitmap(2, object.getBitmap(0));
                                object.setBitmap(3, object.getBitmap(0));
                                break;
                            case "bitmap1":
                                object.setBitmap(1, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap2":
                                object.setBitmap(2, bitmapDecoder.decode(subLine[1]));
                                break;
                            case "bitmap3":
                                object.setBitmap(3, bitmapDecoder.decode(subLine[1]));
                                break;
                        }
                    }
                    objectList.addElement(object);
                }
            }
        } catch (IOException io) {
            Log.e("Nathan", "Object Loading: "+io.toString());
        }
        return objectList;
    }

    public Vector<Terrain> loadTerrainList() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("terrainlist")));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.substring(0, 1).equals("[")) {
                    readLine = readLine.substring(1, readLine.length() - 1);
                    Terrain terrain = new Terrain();
                    String[] subSections = readLine.split("\\|");
                    for(String s : subSections) {
                        String[] subLine = s.split("=");
                        switch(subLine[0]) {
                            case "id":
                                terrain.setId(Integer.parseInt(subLine[1]));
                                break;
                            case "name":
                                terrain.setName(subLine[1]);
                                break;
                            case "walkable":
                                if(subLine[1].equals("true")) {
                                    terrain.setWalkable(true);
                                } else {
                                    terrain.setWalkable(false);
                                }
                                break;
                            case "bitmap":
                                terrain.setBitmap(0, bitmapDecoder.decode(subLine[1]));
                                break;
                        }
                    }
                    terrainList.addElement(terrain);
                }
            }
        } catch (IOException io) {
            Log.e("Nathan", "Terrain Loading: "+io.toString());
        }
        return terrainList;
    }

}
