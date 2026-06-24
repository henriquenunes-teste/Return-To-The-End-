/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.code;

import com.code.models.JacareBaitola;
import com.code.models.Player;
import com.code.models.Upgrade;
import com.code.utils.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author cliente
 */
public class BossBattle1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Player player;
    public JacareBaitola villain;
    
    @FXML
    public ProgressBar player_life;
    
    @FXML
    public ProgressBar villain_life;
    
    @FXML
    public Label player_name;
    
    @FXML
    public Label villain_name;
    
    
    public int turno;
    public int actions;
    public String escolhaPlayer;
    public String escolhaInimigo;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       this.player = new Player(Session.session.getName(),
            new Upgrade(10,0,100,30,40));
       
       this.villain = new JacareBaitola(2000,10,0,20,100);
       
       System.out.println(this.villain.getHealth());
       
       set_ui();
       update_ui();
       
       turno = 1;
       escolhaPlayer = "";
       escolhaInimigo = "";
       actions = 0;
       
       
    }    
    
    private void set_ui(){
        this.player_name.setText(this.player.getName());
        this.villain_name.setText(this.villain.getName());

    }
    
    private void update_ui(){
        player_life.setProgress((double)this.player.getHealth()/this.player.getMaxHealth());
        villain_life.setProgress((double)this.villain.getHealth()/this.villain.getMaxHealth());

    }
    
    private void setTurno(){
    
    }
    
    @FXML
    private void atacar(){
        
        
        actions += 1;
        
        if(actions > 1){
            return;
            
            
        }else{
            this.villain.takeDamage(this.player.getStrength());
        
            update_ui();
            
            System.out.println(this.villain.getHealth());
        }
        
        
        
    }
    
    
    
}
