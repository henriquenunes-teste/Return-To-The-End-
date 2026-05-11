/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.dao;

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
               list.add(new Save(result.getString("name"),result.getTimestamp("last_played").toLocalDateTime(),result.getTimestamp("created_date").toLocalDateTime()));
        }
        
    }
}
