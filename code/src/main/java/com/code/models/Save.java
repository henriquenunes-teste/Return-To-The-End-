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

    public Save(Player player,LocalDateTime last, LocalDateTime created) {
        this.player = player;
        this.created = created;
        this.last = last;
    }
    
    public void update(Player player){
        this.player = player;
        this.last = LocalDateTime.now();
    }
    
}
