/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

/**
 *
 * @author cliente
 */
public class Player extends Character{
    private Upgrade upgrade;

    public Player(Upgrade upgrade, Integer maxHealth, Integer health, Integer level, String name) {
        super(name,maxHealth,health,level);
        this.upgrade = upgrade;
    }
    
    
    public Integer getPoints(){
        return this.upgrade.getTotal();
    }
    
    public Integer getVelocity(){
        return 20+this.upgrade.getVelocity()*5;
    }
    
    public Integer getStrength(){
        return 20+this.upgrade.getStrength()*5;
    }
    
    public Integer getRecup(){
        return 20+this.upgrade.getRecup()*5;
    }
    
    public Integer getDurability(){
        return 20+this.upgrade.getDurability()*5;
    }
    
    public Integer getHealth(){
        return 20+this.upgrade.getHealth()*5;
    }
}
