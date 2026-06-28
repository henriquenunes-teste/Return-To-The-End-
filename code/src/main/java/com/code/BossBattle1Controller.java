package com.code;

import com.code.models.JacareBaitola;
import com.code.models.Player;
import com.code.models.Upgrade;
import com.code.utils.Session;
import java.io.IOException;
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

    // Quantos pontos de upgrade o jogador ganha ao matar este boss
    private static final int PONTOS_POR_BOSS = 5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.player = new Player(Session.session.getName(),
                new Upgrade(10, 0, 100, 30, 40));

        this.villain = new JacareBaitola(2000, 10, 0, 20, 100);

        System.out.println(this.villain.getHealth());

        set_ui();
        update_ui();

        turno = 1;
        escolhaPlayer = "";
        escolhaInimigo = "";
        actions = 0;
    }

    private void set_ui() {
        this.player_name.setText(this.player.getName());
        this.villain_name.setText(this.villain.getName());
    }

    private void update_ui() {
        player_life.setProgress((double) this.player.getHealth() / this.player.getMaxHealth());
        villain_life.setProgress((double) this.villain.getHealth() / this.villain.getMaxHealth());
    }

    private void setTurno() {

    }

    @FXML
    private void atacar() {
        actions += 1;

        if (actions > 1) {
            return;
        } else {
            this.villain.takeDamage(this.player.getStrength());
            update_ui();
            System.out.println(this.villain.getHealth());

            // Verifica se o boss morreu após o dano
            if (this.villain.isDead()) {
                bossDerrotado();
            }
        }
    }

    // Chamado quando o boss chega a 0 de vida, da pra gente adicionar um test de unit so de zoas
    private void bossDerrotado() {
        // Pega o upgrade do player na sessão e adiciona os pontos ganhos
        Upgrade upgrade = Session.session.getPlayer().getUpgrade();
        upgrade.adicionarPontos(PONTOS_POR_BOSS);

        // Atualiza o save com o estado atual do player
        Session.session.update(Session.session.getPlayer());

        // Vai para a tela de upgrades para o jogador gastar os pontos
        try {
            App.setRoot("upgrade");
        } catch (IOException e) {
            System.err.println("Erro ao abrir tela de upgrade: " + e.getMessage());
        }
    }
}