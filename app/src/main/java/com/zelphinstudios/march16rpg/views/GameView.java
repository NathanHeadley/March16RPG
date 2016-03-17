package com.zelphinstudios.march16rpg.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zelphinstudios.march16rpg.entities.NPCEntity;
import com.zelphinstudios.march16rpg.entities.ObjectEntity;
import com.zelphinstudios.march16rpg.entities.TerrainEntity;
import com.zelphinstudios.march16rpg.handlers.InterfaceHandler;
import com.zelphinstudios.march16rpg.handlers.NPCHandler;
import com.zelphinstudios.march16rpg.handlers.ObjectHandler;
import com.zelphinstudios.march16rpg.handlers.TerrainHandler;
import com.zelphinstudios.march16rpg.instances.Player;
import com.zelphinstudios.march16rpg.instances.gui.Button;
import com.zelphinstudios.march16rpg.instances.gui.GUI;
import com.zelphinstudios.march16rpg.instances.gui.TextField;
import com.zelphinstudios.march16rpg.util.BitmapDecoder;

public class GameView extends SurfaceView implements Runnable {

    //region Variables
    private Thread thread = null;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;

    private BitmapDecoder bitmapDecoder;
    private Player player;
    private InterfaceHandler interfaceHandler;
    private ObjectHandler objectHandler;
    private NPCHandler npcHandler;
    private TerrainHandler terrainHandler;
    //endregion

    //region Constructor
    public GameView(Context context_, Player player_, InterfaceHandler interfaceHandler_,
                    ObjectHandler objectHandler_, NPCHandler npcHandler_, TerrainHandler terrainHandler_) {
        super(context_);
        surfaceHolder = getHolder();
        bitmapDecoder = new BitmapDecoder(context_);
        player = player_;
        interfaceHandler = interfaceHandler_;
        objectHandler = objectHandler_;
        npcHandler = npcHandler_;
        terrainHandler = terrainHandler_;
    }
    //endregion

    //region Methods
    public void run() {
        while(running) {
            if(!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surfaceHolder.lockCanvas();

            //region Draw Terrain
            // Fill map with first terrain
            for(int y = 0; y < 12; y++) {
                for(int x = 0; x < 22; x++) {
                    canvas.drawBitmap(terrainHandler.getTerrains().get(0).getBitmap(0),
                            x * 96, y * 96, null);
                }
            }
            // Draw actual terrains
            for(TerrainEntity entity : terrainHandler.getEntities()) {
                float dX = 960 + (entity.getX() - player.getX());
                float dY = 480 + (entity.getY() - player.getY());
                if(dX > 0 && dX <= 1920 && dY >= 0 && dY <= 1080) {
                    canvas.drawBitmap(terrainHandler.getTerrains().get(entity.getId()).getBitmap(0),
                            dX, dY, null);
                }
            }
            //endregion
            //region Draw Objects
            for(ObjectEntity entity : objectHandler.getEntities()) {
                if(entity.isVisible()) {
                    float dX = 960 + (entity.getX() - player.getX());
                    float dY = 480 + (entity.getY() - player.getY());
                    if(dX >= 0 && dX <= 1920 && dY >= 0 && dY <= 1080) {
                        canvas.drawBitmap(objectHandler.getObjects().get(entity.getId()).getBitmap(entity.getDirection()),
                                dX, dY, null);
                    }
                }
            }
            //endregion
            //region Draw NPCs
            for(NPCEntity entity : npcHandler.getEntities()) {
                if(entity.isVisible()) {
                    float dX = 960 + (entity.getX() - player.getX());
                    float dY = 480 + (entity.getY() - player.getY());
                    if(dX >= 0 && dX <= 1920 && dY >= 0 && dY <= 1080) {
                        canvas.drawBitmap(npcHandler.getNpcs().get(entity.getId()).getBitmap(entity.getDirection()),
                                dX, dY, null);
                        if(entity.isInCombat()) {
                            float healthMax = entity.getHealthMax();
                            float healthCurrent = entity.getHealthCurrent() * 100;
                            float percentage = healthCurrent / healthMax;
                            if(percentage >= 100) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_100"),
                                        dX, dY - 20, null);
                            } else if(percentage > 90) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_99"),
                                        dX, dY - 20, null);
                            } else if(percentage > 80) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_90"),
                                        dX, dY - 20, null);
                            } else if(percentage > 70) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_80"),
                                        dX, dY - 20, null);
                            } else if(percentage > 60) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_70"),
                                        dX, dY - 20, null);
                            } else if(percentage > 50) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_60"),
                                        dX, dY - 20, null);
                            } else if(percentage > 40) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_50"),
                                        dX, dY - 20, null);
                            } else if(percentage > 30) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_40"),
                                        dX, dY - 20, null);
                            } else if(percentage > 20) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_30"),
                                        dX, dY - 20, null);
                            } else if(percentage > 10) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_20"),
                                        dX, dY - 20, null);
                            } else if(percentage > 1) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_10"),
                                        dX, dY - 20, null);
                            } else if(percentage <= 0) {
                                canvas.drawBitmap(bitmapDecoder.decode("healthbar_0"),
                                        dX, dY - 20, null);
                            }
                        }
                    }
                }
            }
            //endregion
            //region Draw Player
            canvas.drawBitmap(player.getBitmap(player.getDirection()), 960, 480, null);
            if(player.isInCombat()) {
                float healthMax = player.getHealthMax();
                float healthCurrent = player.getHealthCurrent();
                float percentage = healthCurrent / healthMax;
                if (percentage >= 100) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_100"), 960, 460, null);
                } else if (percentage > 90) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_99"), 960, 460, null);
                } else if (percentage > 80) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_90"), 960, 460, null);
                } else if (percentage > 70) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_80"), 960, 460, null);
                } else if (percentage > 60) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_70"), 960, 460, null);
                } else if (percentage > 50) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_60"), 960, 460, null);
                } else if (percentage > 40) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_50"), 960, 460, null);
                } else if (percentage > 30) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_40"), 960, 460, null);
                } else if (percentage > 20) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_30"), 960, 460, null);
                } else if (percentage > 10) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_20"), 960, 460, null);
                } else if (percentage > 1) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_10"), 960, 460, null);
                } else if (percentage <= 0) {
                    canvas.drawBitmap(bitmapDecoder.decode("healthbar_0"), 960, 460, null);
                }
            }
            //endregion
            //region Draw Interfaces
            interfaceHandler.updateStatInterface();
            for(GUI gui : interfaceHandler.getGUIs()) {
                if(gui.isVisible()) {
                    if(gui.getBackground() != null) {
                        canvas.drawBitmap(gui.getBackground(),
                                gui.getX(), gui.getY(), null);
                    }
                    for(Button button : gui.getButtons()) {
                        if(button.getNotPressed() != null) {
                            if(button.getState() == 0 || button.getPressed() == null) {
                                canvas.drawBitmap(button.getNotPressed(),
                                        button.getX(), button.getY(), null);
                            } else if(button.getState() == 1) {
                                canvas.drawBitmap(button.getPressed(),
                                        button.getX(), button.getY(), null);
                            }
                            button.setState(0);
                        }
                    }
                    for(TextField textField : gui.getTextFields()) {
                        if(textField.isVisible()) {
                            canvas.drawText(textField.getText(),
                                    textField.getX(), textField.getY(), textField.getPaint());
                        }
                    }
                }
            }
            //endregion
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void onResume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPause() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread = null;
    }
    //endregion
}
