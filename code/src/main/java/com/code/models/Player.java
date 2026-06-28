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
    private int id;
    private int coins;
    private int level;


    public Player(String name,Upgrade upgrade) {
        super(name,100,20,20,20,20);
        this.upgrade = upgrade;
        this.coins = 0;
        this.level = 1;
    }
    
    public Player(String name,Upgrade upgrade,int coins, int level) {
        super(name,100,20,20,20,20);
        this.upgrade = upgrade;
        this.coins = coins;
        this.level = level;
    }
    
    
    public Player(String name,int coins, int level) {
        super(name,100,20,20,20,20);
        this.upgrade = new Upgrade(0, 0, 0, 0, 0);
        this.coins = coins;
        this.level = level;
    }
    
    public Player(int id, String name,int coins, int level) {
        super(name,100,20,20,20,20);
        this.upgrade = new Upgrade(0, 0, 0, 0, 0);
        this.coins = coins;
        this.level = level;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Player(String name) {
        super(name,100,20,20,20,20);
        this.upgrade = new Upgrade(0, 0, 0, 0, 0);
        this.coins = 0;
        this.level = 1;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
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
        return this.getVelocity()+this.upgrade.getVelocity()*5;
    }
    
    public Integer getStrength(){
        return this.getStrength()+this.upgrade.getStrength()*5;
    }
    
    public Integer getRecup(){
        return this.getRecup()+this.upgrade.getRecup()*5;
    }
    
    public Integer getDurability(){
        return this.getDurability()+this.upgrade.getDurability()*5;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
}
