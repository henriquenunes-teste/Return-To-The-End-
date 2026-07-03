package com.code.models;

/**
 *
 * @author cliente
 */
public class Character {
    protected Integer maxHealth;
    private Integer health;
    protected Integer strength;
    private Integer velocity;
    protected Integer recup;
    private Integer durability;
    private String name;

    public Character(String name, Integer maxHealth, Integer strength, Integer velocity, Integer recup, Integer durability) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.velocity = velocity;
        this.recup = recup;
        this.durability = durability;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        // Garante que a vida não passe de 0 para negativo
        if (this.health < 0) this.health = 0;
    }

    // Retorna true se o personagem não tem mais vida
    public boolean isDead() {
        return this.health <= 0;
    }
    
    public void recLife(){
        this.health = Math.min(this.maxHealth,this.health+this.recup);
    }

    public Integer getMaxHealth() { return maxHealth; }
    public void setMaxHealth(Integer maxHealth) { this.maxHealth = maxHealth; }
    public Integer getHealth() { return health; }
    public void setHealth(Integer health) { this.health = health; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
    
    
}
