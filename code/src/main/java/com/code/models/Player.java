/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

import com.code.utils.Sprite;
import javafx.scene.image.Image;

/**
 *
 * @author cliente
 */
public class Player extends Character{
    private Upgrade upgrade;
    private Position position;
    private int health ;
    private Sprite sprite;

    public Player(String name,Upgrade upgrade) {
        super(name,100,1,20,20,20,20);
        this.upgrade = upgrade;
        this.health = maxHealth;
    }
    
    public Player(String name,Upgrade upgrade,Image[] frames) {
        super(name,100,1,20,20,20,20);
        this.upgrade = upgrade;
        this.health = maxHealth;
        this.position = new Position(30,30);
        this.sprite = new Sprite(frames,this.position.getX(),this.position.getY());
    }
    
    

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    
    
    
    public Integer getPoints(){
        return this.upgrade.getTotal();
    }
    
    public Integer getVelocity(){
        return this.velocity+this.upgrade.getVelocity()*5;
    }
    
    public Integer getStrength(){
        return this.strength+this.upgrade.getStrength()*5;
    }
    
    public Integer getRecup(){
        return this.recup+this.upgrade.getRecup()*5;
    }
    
    public Integer getDurability(){
        return this.durability+this.upgrade.getDurability()*5;
    }
    
    public Integer getHealth(){
        return this.maxHealth+this.upgrade.getHealth()*5;
    }
}
