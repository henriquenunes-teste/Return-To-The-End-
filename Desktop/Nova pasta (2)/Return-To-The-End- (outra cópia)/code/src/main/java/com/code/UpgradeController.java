package com.code;

import com.code.dao.PlayerDAO;
import com.code.models.Player;
import com.code.models.Upgrade;
import com.code.utils.GlobalData;
import com.code.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class UpgradeController implements Initializable {

    @FXML private Label forcaLabel;
    @FXML private Label vidaLabel;
    @FXML private Label recupLabel;
    @FXML private Label coinsLabel;          // coins disponíveis para gastar
    @FXML private Label coinsBloqueadasLabel; // coins acumuladas ainda bloqueadas

    private Upgrade upgrade;
    private Player player;
    private PlayerDAO dao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dao    = new PlayerDAO();
        this.player = Session.session.getPlayer();

        this.upgrade = dao.getUpgrade(player);
        if (this.upgrade == null) {
            this.upgrade = new Upgrade(0, 0, 0);
            player.setUpgrade(this.upgrade);
        }

        dao.getCoins(player);
        atualizarUI();
    }

    private void atualizarUI() {
        forcaLabel.setText(String.valueOf(upgrade.getStrength()));
        vidaLabel.setText(String.valueOf(upgrade.getHealth()));
        recupLabel.setText(String.valueOf(upgrade.getRecup()));

        if (coinsLabel != null) {
            coinsLabel.setText("Coins: " + player.getCoins());
        }
        if (coinsBloqueadasLabel != null) {
            coinsBloqueadasLabel.setText("Em jogo: " + GlobalData.coinsBloqueadas);
        }
    }

    @FXML
    private void adicionarForca() {
        if (player.getCoins() < 5) return;
        upgrade.setStrength(upgrade.getStrength() + 1);
        dao.decrementCoins(player, 5);
        player.setCoins(player.getCoins() - 5);
        atualizarUI();
    }

    @FXML
    private void removerForca() {
        if (upgrade.getStrength() <= 0) return;
        upgrade.setStrength(upgrade.getStrength() - 1);
        dao.addCoins(player, 5);
        player.setCoins(player.getCoins() + 5);
        atualizarUI();
    }

    @FXML
    private void adicionarVida() {
        if (player.getCoins() < 5) return;
        upgrade.setHealth(upgrade.getHealth() + 1);
        dao.decrementCoins(player, 5);
        player.setCoins(player.getCoins() - 5);
        atualizarUI();
    }

    @FXML
    private void removerVida() {
        if (upgrade.getHealth() <= 0) return;
        upgrade.setHealth(upgrade.getHealth() - 1);
        dao.addCoins(player, 5);
        player.setCoins(player.getCoins() + 5);
        atualizarUI();
    }

    @FXML
    private void adicionarRecup() {
        if (player.getCoins() < 5) return;
        upgrade.setRecup(upgrade.getRecup() + 1);
        dao.decrementCoins(player, 5);
        player.setCoins(player.getCoins() - 5);
        atualizarUI();
    }

    @FXML
    private void removerRecup() {
        if (upgrade.getRecup() <= 0) return;
        upgrade.setRecup(upgrade.getRecup() - 1);
        dao.addCoins(player, 5);
        player.setCoins(player.getCoins() + 5);
        atualizarUI();
    }

    @FXML
    public void save() {
        dao.setUpgrade(player, upgrade);
    }

    @FXML
    public void menu() throws IOException {
        save();
        App.setRoot("primary");
    }

    @FXML
    public void back() throws IOException {
        save();
        App.setRoot("gameplay");
    }
}