/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

/**
 *
 * @author cliente
 */
public class Upgrade {
    private Integer strength;
    private Integer velocity;
    private Integer health;
    private Integer recup;
    private Integer durability;

    public Upgrade(Integer strength,Integer velocity, Integer health, Integer recup, Integer durability) {
        this.strength = strength;
        this.velocity = velocity;
        this.health = health;
        this.recup = recup;
        this.durability = durability;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getRecup() {
        return recup;
    }

    public void setRecup(Integer recup) {
        this.recup = recup;
    }

    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }
    
    
    public Integer getTotal(){
        return this.getStrength() + this.getDurability() + this.getHealth() + this.getRecup() + this.getVelocity();
    }

}
