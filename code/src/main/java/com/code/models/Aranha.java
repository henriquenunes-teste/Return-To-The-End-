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
public class Aranha extends Character implements IBoss {

    
    public Aranha(String name, Integer maxHealth, Integer level, Integer strength, Integer velocity, Integer recup, Integer durability) {
        super(name, maxHealth, level, strength, velocity, recup, durability);
    }

    @Override
    public void attack1(Player p) {
        p.takeDamage(5);
    }

    @Override
    public void attack2(Player p) {
        p.takeDamage(15);
    }

    @Override
    public void attack3(Player p) {
        p.takeDamage(700);
    }

    @Override
    public void attackOrder(Player p) {
        for(int i = 0; i < 100;i++){
            this.attack1(p);
        }
        
         for(int i = 0; i < 10;i++){
            this.attack2(p);
        }
         
        this.attack3(p);
    }
    
}
