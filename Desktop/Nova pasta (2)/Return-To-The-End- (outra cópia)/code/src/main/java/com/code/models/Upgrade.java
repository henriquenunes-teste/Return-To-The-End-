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

    // Pontos disponíveis para gastar — ganhos ao derrotar bosses
    private Integer pontosDisponiveis;

    public Upgrade(Integer strength, Integer velocity, Integer health, Integer recup, Integer durability) {
        this.strength = strength;
        this.velocity = velocity;
        this.health = health;
        this.recup = recup;
        this.durability = durability;
        this.pontosDisponiveis = 0;
    }
    
    public Upgrade(Integer strength, Integer health, Integer recup) {
        this.strength = strength;
        this.health = health;
        this.recup = recup;
    }

    // Adiciona pontos ao derrotar um boss
    public void adicionarPontos(int pontos) {
        this.pontosDisponiveis += pontos;
    }

    public Integer getPontosDisponiveis() {
        return pontosDisponiveis;
    }

    public void setPontosDisponiveis(Integer pontosDisponiveis) {
        this.pontosDisponiveis = pontosDisponiveis;
    }

    public Integer getStrength() { 
        return strength; }
    
    public void setStrength(Integer strength){
        this.strength = strength; }
    
    public Integer getVelocity() { 
        return velocity; }
    
    public void setVelocity(Integer velocity) {
        this.velocity = velocity; }
    
    public Integer getHealth() {
        return health; }
    
    public void setHealth(Integer health) {
        this.health = health; }
    
    public Integer getRecup() { 
        return recup; }
    
    public void setRecup(Integer recup) { 
        this.recup = recup; }
    
    public Integer getDurability() {
        return durability; }
    
    public void setDurability(Integer durability) { 
        this.durability = durability; }

    // Soma total de pontos já gastos nos atributos
    public Integer getTotal() {
        return this.strength + this.velocity + this.health + this.recup + this.durability;
    }
}