/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.code;

import com.code.utils.Session;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author aluno
 */
public class GameplayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public Label text;
    
    @FXML
    public ImageView player;
    
    @FXML
    public AnchorPane inicial;
    
    @FXML
    public Rectangle villain;
    
    public ArrayList<Circle> balas = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicial.sceneProperty().addListener((obs,oldScene,newScene)->{
            if(newScene != null){
                System.out.println(player.getLayoutY());
                newScene.setOnKeyPressed((e)->{
                    if(e.getCode()==KeyCode.W && player.getLayoutY()>= 0){
                 
                        
                        player.setLayoutY(player.getLayoutY()-20);
                    }
                    
                    if(e.getCode()==KeyCode.S && player.getLayoutY()+20<=400){
                 
                        
                        player.setLayoutY(player.getLayoutY()+20);
                    }
                    
                    if(e.getCode()==KeyCode.A && player.getLayoutX()>=0){
                 
                        
                        player.setLayoutX(player.getLayoutX()-20);
                    }
                    
                     if(e.getCode()==KeyCode.D && player.getLayoutX()+20< 600){
                 
                        
                        player.setLayoutX(player.getLayoutX()+20);
                    }
                     
                     
                });
                
                
            }
        });
    }    
    
}
