/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.code;

import com.code.dao.SaveDAO;
import com.code.models.Save;
import com.code.utils.AlertUtils;
import com.code.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author cliente
 */
public class SelectionController implements Initializable {
    ObservableList<Save> saves= FXCollections.observableArrayList();
    
    @FXML
    public ListView<Save> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
                // TODO
            SaveDAO saveDao = new SaveDAO();

            saveDao.listAll(saves);
            
            list.setItems(saves);
        }catch(SQLException e){}
        
        list.setOnMouseClicked(e->{
            if(e.getClickCount() >= 2){
                Session.session = list.getSelectionModel().getSelectedItem();
                System.out.println(Session.session.getName());
                
                try{
                    go();
                }catch(IOException err){
                    AlertUtils.error("exception", err);
                }
            }
        });
        
    }    
    
    
    public void go() throws IOException{
        App.setRoot("gameplay");
    }
    
    @FXML
    public void upgrades() throws IOException{
        App.setRoot("upgrade");
    }
    
    @FXML
    public void play(){
        
    }
    
    @FXML
    public void back() throws IOException {
        App.setRoot("primary");
    }
}
