/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author cliente
 */
public class PirataCibernetico extends Character implements IBoss {

    
    public PirataCibernetico(String name, Integer maxHealth, Integer strength, Integer velocity, Integer recup, Integer durability) {
        super(name, maxHealth,  strength, velocity, recup, durability);
    }

    @Override
    public void attack1(Player p) {
        p.takeDamage(500);
    }

    @Override
    public void attack2(Player p) {
        p.takeDamage(1500);
    }

    @Override
    public void attack3(Player p) {
        p.takeDamage(7000);
    }

    @Override
    public void attackOrder(Player p) {
        for(int i = 0; i < 3;i++){
            this.attack1(p);
        }
        
        
        this.attack2(p);
        
         
        this.attack3(p);
    }
    
}
