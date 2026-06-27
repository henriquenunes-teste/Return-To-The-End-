/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.dao;

import com.code.models.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.code.models.Run;
import com.code.models.Save;
import java.sql.ResultSet;
import javafx.collections.ObservableList;

/**
 *
 * @author cliente
 */
public class RunDAO extends ConnectionFactory{
    private Connection connection;
    
    public RunDAO(){
        try{
            this.connection = createConnection("jdbc:postgresql://localhost:5432/rtte", "postgres", "senha123");
            System.out.println("Conectado com sucesso!");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public boolean create(Run run) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO run(time,gain,exp,player_id) VALUES (?,?,?,?)");
        stmt.setLong(1, run.getTime());
        stmt.setInt(2, run.getGain());
        stmt.setInt(3, run.getExp());
        stmt.setInt(4, run.getPlayer().getId());
        return stmt.execute();
    }
    
    public void listAll(ObservableList<Run> list) throws SQLException {
        PlayerDAO dao = new PlayerDAO();
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM run");
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
               list.add(new Run(result.getInt("id") ,result.getLong("time"),result.getInt("gain"),result.getInt("exp"),dao.getPlayerByID(result.getInt("player_id"))));
        }
        
    }
}
