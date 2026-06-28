package com.code;

import com.code.dao.RunDAO;
import com.code.models.JacareBaitola;
import com.code.models.Player;
import com.code.models.Pocao;
import com.code.models.Run;
import com.code.utils.GlobalData;
import com.code.utils.Session;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class BossBattle1Controller implements Initializable {

    public Player player;
    public JacareBaitola villain;

    @FXML public ProgressBar player_life;
    @FXML public ProgressBar villain_life;
    @FXML public Label player_name;
    @FXML public Label villain_name;

    public int turno;
    public int actions;
    public String escolhaPlayer;
    public String escolhaVillain;
    public long inicio;
    public boolean ended;
    public boolean victory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.player  = Session.session.getPlayer();
        this.villain = new JacareBaitola(2000, 10, 0, 20, 100);

        set_ui();
        update_ui();

        turno         = 1;
        escolhaPlayer = "";
        escolhaVillain = "";
        actions       = 0;
        inicio        = System.currentTimeMillis();
        ended         = false;

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (checkEnd()) {
                    setResult();
                    stop();
                }
            }
        };
        at.start();
    }

    private void setResult() {
        try {
            GlobalData.victory = victory;
            new RunDAO().create(new Run(System.currentTimeMillis() - inicio, 120, 170, player));

            // Dropa poção só na vitória
            if (victory) {
                int upgradeVida = player.getUpgrade().getHealth();
                Pocao pocao = new Pocao(upgradeVida);
                GlobalData.adicionarPocao(pocao);
                System.out.println("Poção dropada: " + pocao);
            }
        } catch (SQLException ex) {
            System.getLogger(BossBattle1Controller.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void set_ui() {
        player_name.setText(player.getName());
        villain_name.setText(villain.getName());
    }

    private void update_ui() {
        player_life.setProgress(Math.max(0, Math.min(1,
            (double) player.getHealth()  / player.getMaxHealth())));
        villain_life.setProgress(Math.max(0, Math.min(1,
            (double) villain.getHealth() / villain.getMaxHealth())));
    }

    private void revidar() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            if (!escolhaPlayer.isEmpty()) {
                if (escolhaPlayer.equals("wrongAttack")) {
                    player.takeDamage(villain.getStrength());
                } else if (escolhaPlayer.equals("defend")) {
                    player.recLife();
                    villain.recLife();
                } else {
                    villain.takeDamage(player.getStrength());
                }
                update_ui();
                actions = 0;
                turno++;
            }
        });
        pause.play();
    }

    @FXML
    private void atacar() {
        if (++actions > 1) return;
        escolhaPlayer = ThreadLocalRandom.current().nextInt(1, 3) == 1 ? "winAttack" : "wrongAttack";
        revidar();
    }

    @FXML
    private void defend() {
        if (++actions > 1) return;
        escolhaPlayer = "defend";
        revidar();
    }

    private boolean checkEnd() {
        if (player.getHealth() == 0 || villain.getHealth() == 0) {
            ended   = true;
            victory = player.getHealth() != 0;
            return true;
        }
        return false;
    }
}