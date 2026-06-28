/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.code;

import com.code.dao.PlayerDAO;
import com.code.dao.RunDAO;
import com.code.models.JacareBaitola;
import com.code.models.Player;
import com.code.models.Run;
import com.code.models.Upgrade;
import com.code.utils.AlertUtils;
import com.code.utils.GlobalData;
import com.code.utils.Session;
import java.net.URL;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
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
    public String escolhaVillain;
    
    public long inicio;
    public boolean ended;
    public boolean victory;
    

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       this.player = Session.session.getPlayer();
       
       this.villain = new JacareBaitola(2000,10,0,20,100);
       
       System.out.println(this.villain.getHealth());
       System.out.println(this.player.getHealth());

       
       set_ui();
       update_ui();
       
       turno = 1;
       escolhaPlayer = "";
       escolhaVillain = "";
       actions = 0;
       inicio = System.currentTimeMillis();
       ended = false;
       
        AnimationTimer at = new AnimationTimer() {
           @Override
           public void handle(long l) {
              if(checkEnd()){
                  setResult();
              }
           }

           
           
           
        };
       
    }    
    
  
    
    private void setResult(){
        try {
            RunDAO dao = new RunDAO();
            GlobalData.victory = victory;
            dao.create(new Run(System.currentTimeMillis()-inicio,120,170,player));
        } catch (SQLException ex) {
            System.getLogger(BossBattle1Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private void set_ui(){
        this.player_name.setText(this.player.getName());
        this.villain_name.setText(this.villain.getName());

    }
    
    private void update_ui(){
        double playerProgress = (double) player.getHealth() / player.getMaxHealth();
        double villainProgress = (double) villain.getHealth() / villain.getMaxHealth();

        player_life.setProgress(Math.max(0, Math.min(1, playerProgress)));
        villain_life.setProgress(Math.max(0, Math.min(1, villainProgress)));

    }
    
    private void revidar(){
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(event -> {
            if(!escolhaPlayer.equals("")){
                if(escolhaPlayer.equals("wrongAttack")){
                    escolhaVillain = "attack";
                    player.takeDamage(villain.getStrength());
                }else if(escolhaPlayer.equals("defend")){
                    escolhaVillain = "attack";
                    player.recLife();
                    villain.recLife();
                }else{
                    villain.takeDamage(player.getStrength());
                }

                update_ui();
                
                
                actions = 0;

                turno += 1;
            }
        });

        pause.play();
    }
    
    @FXML
    private void atacar(){
        
        
        actions += 1;
        
        if(actions > 1){
            return;
            
            
        }else{
            
            
            
            
            int chance = ThreadLocalRandom.current().nextInt(1,3);
            
            if(chance == 1){
                escolhaPlayer = "winAttack";
            }else{
                escolhaPlayer = "wrongAttack";
            }
            
            revidar();
            
            
            System.out.println(this.villain.getHealth());
            System.out.println(this.player.getHealth());
        }
        
        
        
    }
    
    @FXML
    private void defend(){
        
        
        actions += 1;
        
        if(actions > 1){
            return;
            
            
        }else{
            
            
            escolhaPlayer = "defend";
            
            
            revidar();
            
            
            System.out.println(this.villain.getHealth());
            System.out.println(this.player.getHealth());
        }
        
        
        
    }
    
    private boolean checkEnd(){
       
        
        if(player.getHealth().equals(0) || villain.getHealth().equals(0)){
                ended = true;
                
                if(player.getHealth().equals(0)){
                    victory = false;
                }else{
                    victory = true;
                }
                
                
                
                return true;
            
        }
        
        
        return false;
    }
    
}