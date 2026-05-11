/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author cliente
 */
public abstract class ConnectionFactory {
    protected Connection createConnection(String url, String username, String password) throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
}
