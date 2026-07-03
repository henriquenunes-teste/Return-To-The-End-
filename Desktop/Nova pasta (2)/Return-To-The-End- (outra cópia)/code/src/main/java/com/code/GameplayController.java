package com.code;

import com.code.utils.AlertUtils;
import com.code.utils.GlobalData;
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
    @FXML
    private Label bossLabel; // mostra qual o próximo boss

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var player = Session.session.getPlayer();

        if (coinsLabel      != null) coinsLabel.setText(String.valueOf(player.getCoins()));
        if (playerNameLabel != null) playerNameLabel.setText(player.getName());
        if (levelLabel      != null) levelLabel.setText("Nível " + player.getLevel());
        if (bossLabel       != null) bossLabel.setText("Próximo: " + nomeBossAtual());
    }

    /** Nome legível do boss atual para mostrar na tela. */
    private String nomeBossAtual() {
        if (GlobalData.jogoCompleto()) return "— Jogo completo! —";
        if (GlobalData.bossAtual == 1) return "Jacaré Baitola";
        if (GlobalData.bossAtual == 2) return "Cerberus";
        if (GlobalData.bossAtual == 3) return "Pirata Cibernético";
        return "Aranha";
    }

    @FXML
    public void jogar() throws IOException {
        if (GlobalData.jogoCompleto()) {
            AlertUtils.info("Fim de jogo", "Você já derrotou todos os bosses!");
            return;
        }
        App.setRoot("boss_battle_1");
    }

    @FXML
    public void abrirUpgrades()   throws IOException { App.setRoot("upgrade");   }
    @FXML 
    public void abrirInventario() throws IOException { App.setRoot("inventory"); }
    @FXML 
    public void abrirRanking()    throws IOException { App.setRoot("ranking");   }
    @FXML 
    public void abrirCreditos()   throws IOException { App.setRoot("creditos");  }
    @FXML
    public void menu()            throws IOException { App.setRoot("primary");   }
}