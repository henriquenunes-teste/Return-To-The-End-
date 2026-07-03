/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author cliente
 */
public class Sprite {
    private Image[] frames;
    private ImageView view;
    
    private final long DELAY = 2_000_000;
    private int FRAME_INDEX = 0;
    private long LAST_UPDATE = 0;
    
    
    public Sprite(Image[] frames, double x, double y){
        this.frames = frames;
        this.view = new ImageView(frames[0]);
        
        this.view.setX(x);
        this.view.setY(y);
    }
    
    public void update(long now){
        if(now - LAST_UPDATE > DELAY){
            FRAME_INDEX = (FRAME_INDEX+1)%this.frames.length;
            this.view.setImage(frames[FRAME_INDEX]);
            LAST_UPDATE = now;
        }
    }
    
    public ImageView getView(){
        return this.view;
    }
    
}
