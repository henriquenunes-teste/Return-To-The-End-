/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.code;

import com.code.dao.SaveDAO;
import com.code.models.Save;
import com.code.utils.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author cliente
 */
public class FormularioController implements Initializable {
    @FXML
    private TextField name;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void back() throws IOException{
        App.setRoot("secondary");
    }
    
    @FXML
    private void save() throws IOException{
        try{
            (new SaveDAO()).create(new Save(name.getText(),LocalDateTime.now(),LocalDateTime.now()));
            AlertUtils.info("Sucesso","Criado com sucesso!");
            App.setRoot("secondary");
        }catch(SQLException e){
            AlertUtils.error("Erro!", e);
        }
    }

}
