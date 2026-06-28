/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.dao;

import com.code.models.Player;
import com.code.models.Save;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.ObservableList;

/**
 *
 * @author cliente
 */
public class PlayerDAO extends ConnectionFactory{
    private Connection connection;
    
    public PlayerDAO(){
        try{
            this.connection = createConnection("jdbc:postgresql://localhost:5432/rtte", "postgres", "senha123");
            System.out.println("Conectado com sucesso!");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public boolean create(Player player) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO players(name,coins,level) VALUES (?,?,?)");
        stmt.setString(1, player.getName());
        stmt.setInt(2, player.getCoins());
        stmt.setInt(3, player.getLevel());
        return stmt.execute();
    }
    
    public void listAll(ObservableList<Player> list) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM players");
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
               
               list.add(new Player(result.getInt("id"),result.getString("name"),result.getInt("coins"),result.getInt("level")));
        }
        
    }
    
    public int getByName(String name) throws SQLException,RuntimeException{
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM players WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        
        if(result.next()){
            return result.getInt("id");
        }else{
            throw new RuntimeException("This player does not exists!");
        }
    }
    
    public Player getPlayerByName(String name) throws SQLException,RuntimeException{
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM players WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        
        if(result.next()){
            return new Player(result.getInt("id"),result.getString("name"),result.getInt("coins"),result.getInt("level"));
        }else{
            throw new RuntimeException("This player does not exists!");
        }
    }
    
    public Player getPlayerByID(int id) throws SQLException,RuntimeException{
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM players WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        
        if(result.next()){
            return new Player(result.getInt("id"),result.getString("name"),result.getInt("coins"),result.getInt("level"));
        }else{
            throw new RuntimeException("This player does not exists!");
        }
    }
}
