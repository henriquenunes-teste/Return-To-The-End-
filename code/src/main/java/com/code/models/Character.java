/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

/**
 *
 * @author cliente
 */
public class Character {
    protected Integer maxHealth;
    private Integer health;
    private Integer level;
    protected Integer strength;
    protected Integer velocity;
    protected Integer recup;
    protected Integer durability;
    private String name;

    public Character(String name, Integer maxHealth, Integer level, Integer strength, Integer velocity, Integer recup, Integer durability) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.level = level;
        this.strength = strength;
        this.velocity = velocity;
        this.recup = recup;
        this.durability = durability;
    }
    
    public void takeDamage(int damage){
        this.health -=  damage;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
