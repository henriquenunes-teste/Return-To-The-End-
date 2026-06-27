/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.models;

import java.sql.Time;

/**
 *
 * @author cliente
 */
public class Run {
    private int id;
    private long time;
    private Integer gain;
    private Integer exp;
    private Player player;

    public Run(int id, long time, Integer gain, Integer exp, Player player) {
        this.time = time;
        this.gain = gain;
        this.exp = exp;
        this.player = player;
        this.id = id;
    }
    
    public Run(long time, Integer gain, Integer exp, Player player) {
        this.time = time;
        this.gain = gain;
        this.exp = exp;
        this.player = player;
        this.id = 0;
    }

    public int getID(){
        return this.id;
    }
    
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Integer getGain() {
        return gain;
    }

    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
