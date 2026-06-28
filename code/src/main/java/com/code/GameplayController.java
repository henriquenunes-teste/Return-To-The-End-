package com.code;

import com.code.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GameplayController implements Initializable {

    @FXML
    private Label coinsLabel;
    @FXML 
    private Label playerNameLabel;
    @FXML
    private Label levelLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var player = Session.session.getPlayer();

        if (coinsLabel    != null) coinsLabel.setText(String.valueOf(player.getCoins()));
        if (playerNameLabel != null) playerNameLabel.setText(player.getName());
        if (levelLabel    != null) levelLabel.setText("Nível " + player.getLevel());
    }

    @FXML
    public void abrirUpgrades()  throws IOException { App.setRoot("upgrade");   }
    @FXML
    public void abrirInventario() throws IOException { App.setRoot("inventory"); }
    @FXML 
    public void abrirRanking()   throws IOException { App.setRoot("ranking");   }
    @FXML
    public void jogar()          throws IOException { App.setRoot("selection"); }
    @FXML 
    public void menu()           throws IOException { App.setRoot("primary");   }
}