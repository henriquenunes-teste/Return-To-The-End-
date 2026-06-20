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
public class Cerberus extends Character implements IBoss {

    
    public Cerberus(String name, Integer maxHealth, Integer level, Integer strength, Integer velocity, Integer recup, Integer durability) {
        super(name, maxHealth, level, strength, velocity, recup, durability);
    }

    @Override
    public void attack1(Player p) {
        p.takeDamage(1800);
    }

    @Override
    public void attack2(Player p) {
        p.takeDamage(900);
    }

    @Override
    public void attack3(Player p) {
        p.takeDamage(1200);
    }

    @Override
    public void attackOrder(Player p) {
        this.attack1(p);
        
        
        for(int i = 0; i < 3;i++){
            this.attack2(p);
        }
        
         
        for(int i = 0; i < 2;i++){
            this.attack3(p);
        }
    }
    
}
