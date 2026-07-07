/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.dao;

import com.code.models.Player;
import com.code.models.Upgrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author cliente
 */
public class UpgradeDAO extends ConnectionFactory{
    private Connection connection;
    
    public UpgradeDAO(){ 
        try{
            this.connection = createConnection("jdbc:postgresql://localhost:5432/rtte", "postgres", "senha123");
            System.out.println("Conectado com sucesso!");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public boolean create(Player player) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO upgrade(strength,health,recup,player_id) VALUES (?,?,?,?)");
        stmt.setInt(1, 0);
        stmt.setInt(2, 0);
        stmt.setInt(3, 0);
        stmt.setInt(4, player.getId());
        return stmt.execute();
    }
    //Altera os daos pro seu banco rodar ja que alterei pros meus
    public void listAll(ObservableList<Upgrade> list) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM upgrade");
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
               
               list.add(new Upgrade(result.getInt("strength"),result.getInt("health"),result.getInt("recup")));
        }
        
    }
}
