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
public class JacareBaitola extends Character implements IBoss {

    
    public JacareBaitola(Integer maxHealth, Integer strength, Integer velocity, Integer recup, Integer durability) {
        super("Jacaré Baitola", maxHealth, strength, velocity, recup, durability);
    }

    @Override
    public void attack1(Player p) {
        p.takeDamage(80);
    }

    @Override
    public void attack2(Player p) {
        p.takeDamage(10);
    }

    @Override
    public void attack3(Player p) {
        p.takeDamage(300);
    }

    @Override
    public void attackOrder(Player p) {
        this.attack1(p);
        for(int i = 0; i < 20;i++){
            this.attack2(p);
        }
        this.attack3(p);
        this.attack3(p);
    }

    
}
