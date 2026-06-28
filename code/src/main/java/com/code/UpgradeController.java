package com.code;

import com.code.models.Upgrade;
import com.code.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author cliente
 */
public class UpgradeController implements Initializable {

    @FXML private Label pontosLabel;
    @FXML private Label forcaLabel;
    @FXML private Label velocidadeLabel;
    @FXML private Label vidaLabel;
    @FXML private Label recupLabel;
    @FXML private Label durabLabel;

   
    private int pontosDisponiveis;

    // Referencia o upgrade do player da sessão atual
    private Upgrade upgrade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Pega o upgrade do player que está na sessão
        this.upgrade = Session.session.getPlayer().getUpgrade();

        // getTotal() retorna a soma de todos os pontos já gastos
        // Os pontos disponíveis = pontos ganhos ao matar bosses, pontos já gastos.
        // Como ainda não temos um campo separado de "pontos ganhos", usa o total
        // do save como referência — Coloca aqui quando integrar com a lógica de boss.
        this.pontosDisponiveis = Session.session.getPlayer().getPoints();

        atualizarUI();
    }

    // Atualiza todos os labels da tela com os valores atuais
    private void atualizarUI() {
        pontosLabel.setText("Pontos: " + pontosDisponiveis);
        forcaLabel.setText(String.valueOf(upgrade.getStrength()));
        velocidadeLabel.setText(String.valueOf(upgrade.getVelocity()));
        vidaLabel.setText(String.valueOf(upgrade.getHealth()));
        recupLabel.setText(String.valueOf(upgrade.getRecup()));
        durabLabel.setText(String.valueOf(upgrade.getDurability()));
    }

  

    @FXML
    private void adicionarForca() {
        if (pontosDisponiveis <= 0) return; // sem pontos, não faz nada
        upgrade.setStrength(upgrade.getStrength() + 1);
        pontosDisponiveis--;
        atualizarUI();
    }

    @FXML
    private void removerForca() {
        if (upgrade.getStrength() <= 0) return; // não deixa ir abaixo de 0
        upgrade.setStrength(upgrade.getStrength() - 1);
        pontosDisponiveis++;
        atualizarUI();
    }

 
    @FXML
    private void adicionarVelocidade() {
        if (pontosDisponiveis <= 0) return;
        upgrade.setVelocity(upgrade.getVelocity() + 1);
        pontosDisponiveis--;
        atualizarUI();
    }

    @FXML
    private void removerVelocidade() {
        if (upgrade.getVelocity() <= 0) return;
        upgrade.setVelocity(upgrade.getVelocity() - 1);
        pontosDisponiveis++;
        atualizarUI();
    }

    @FXML
    private void adicionarVida() {
        if (pontosDisponiveis <= 0) return;
        upgrade.setHealth(upgrade.getHealth() + 1);
        pontosDisponiveis--;
        atualizarUI();
    }

    @FXML
    private void removerVida() {
        if (upgrade.getHealth() <= 0) return;
        upgrade.setHealth(upgrade.getHealth() - 1);
        pontosDisponiveis++;
        atualizarUI();
    }


    @FXML
    private void adicionarRecup() {
        if (pontosDisponiveis <= 0) return;
        upgrade.setRecup(upgrade.getRecup() + 1);
        pontosDisponiveis--;
        atualizarUI();
    }

    @FXML
    private void removerRecup() {
        if (upgrade.getRecup() <= 0) return;
        upgrade.setRecup(upgrade.getRecup() - 1);
        pontosDisponiveis++;
        atualizarUI();
    }


    @FXML
    private void adicionarDurabilidade() {
        if (pontosDisponiveis <= 0) return;
        upgrade.setDurability(upgrade.getDurability() + 1);
        pontosDisponiveis--;
        atualizarUI();
    }

    @FXML
    private void removerDurabilidade() {
        if (upgrade.getDurability() <= 0) return;
        upgrade.setDurability(upgrade.getDurability() - 1);
        pontosDisponiveis++;
        atualizarUI();
    }


    @FXML
    public void menu() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void back() throws IOException {
        App.setRoot("selection");
    }
}