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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author cliente
 */
public class SaveDAO extends ConnectionFactory{
    private Connection connection;
    
    public SaveDAO(){
        try{
            this.connection = createConnection("jdbc:postgresql://localhost:5432/rtte", "postgres", "senha123");
            System.out.println("Conectado com sucesso!");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public boolean create(Save save) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO save(name,last_played,created_date) VALUES (?,?,?)");
        stmt.setString(1, save.getName());
        stmt.setTimestamp(2, Timestamp.valueOf(save.getLast()));
        stmt.setTimestamp(3, Timestamp.valueOf(save.getCreated()));
        return stmt.execute();
    }
    
    public void listAll(ObservableList<Save> list) throws SQLException {
        
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM save");
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
               list.add(new Save(result.getInt("id") ,result.getString("name"),result.getTimestamp("last_played").toLocalDateTime(),result.getTimestamp("created_date").toLocalDateTime()));
        }
        
    }
    
    public void updatePlayer(Integer id,Player player) throws SQLException {
        PlayerDAO dao = new PlayerDAO();
        int player_id = dao.getByName(player.getName());
        
        PreparedStatement stmt = this.connection.prepareStatement("UPDATE save SET player_id = ? WHERE id = ?");
        
        stmt.setInt(1, player_id);
        stmt.setInt(2, id);
        
        stmt.executeUpdate();
    }
    
    public Player getPlayerBySave(int id) throws SQLException {
        
        PreparedStatement stmt = this.connection.prepareStatement("SELECT players.* FROM save INNER JOIN players ON save.player_id = players.id");
        ResultSet result = stmt.executeQuery();
        
        
        if(result.next()){
            return new Player(result.getInt("id"),result.getString("name"),result.getInt("coins"),result.getInt("level"));
        }else{
            throw new RuntimeException("This player does not exists!");

        }
        
        
    }    
}
