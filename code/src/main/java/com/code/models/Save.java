/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author cliente
 */
public class Save {
    private LocalDateTime last;
    private LocalDateTime created;
    private Player player;
    private String name;
    private int id;

    public Save(String name,LocalDateTime last, LocalDateTime created) {
        this.player = new Player(name,new Upgrade(0,0,0,0,0));
        this.created = created;
        this.id = 0;
        this.last = last;
        this.name = name;
    }
    
    public Save(String name,LocalDateTime last, LocalDateTime created,Player player) {
        this.player = player;
        this.created = created;
        this.id = 0;
        this.last = last;
        this.name = name;
    }
    
    public Save(int id, String name,LocalDateTime last, LocalDateTime created) {
        this.player = new Player(name,new Upgrade(0,0,0,0,0));
        this.created = created;
        this.id = id;
        this.last = last;
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "Nome:"+this.getName()+"\nLast:"+this.getLast()+"\nCreated:"+this.getCreated();
    }
    
    public void update(Player player){
        this.player = player;
        this.last = LocalDateTime.now();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDateTime getLast() {
        return last;
    }

    public void setLast(LocalDateTime last) {
        this.last = last;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
    
}
