package com.code;

import com.code.models.Pocao;
import com.code.utils.GlobalData;
import com.code.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class InventoryController implements Initializable {

    @FXML private Label playerNameLabel;
    @FXML private Label coinsLabel;
    @FXML private Label levelLabel;
    @FXML private Label estoqueLabel;
    @FXML private ListView<String> itemListView;
    @FXML private Label emptyLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var player = Session.session.getPlayer();
        playerNameLabel.setText(player.getName());
        coinsLabel.setText("Coins: " + player.getCoins());
        levelLabel.setText("Nível " + player.getLevel());
        carregarPocoes();
    }

    private void carregarPocoes() {
        ObservableList<String> linhas = FXCollections.observableArrayList();

        for (int i = 0; i < GlobalData.pocoes.size(); i++) {
            linhas.add((i + 1) + ".  " + GlobalData.pocoes.get(i).toString());
        }

        int disponiveis = GlobalData.quantidadePocoes();
        estoqueLabel.setText(disponiveis + " poção(ões) disponível(is)");

        if (linhas.isEmpty()) {
            emptyLabel.setVisible(true);
            itemListView.setVisible(false);
        } else {
            itemListView.setItems(linhas);
            itemListView.setVisible(true);
            emptyLabel.setVisible(false);
        }
    }

    @FXML
    private void usarPocao() {
        Pocao pocao = GlobalData.pegarPocaoDisponivel();
        if (pocao == null) {
            estoqueLabel.setText("Nenhuma poção disponível!");
            return;
        }
        pocao.usar(Session.session.getPlayer());
        carregarPocoes(); // atualiza a lista na tela
    }

    @FXML
    public void voltar() throws IOException { App.setRoot("gameplay"); }
    @FXML
    public void menu()   throws IOException { App.setRoot("primary");  }
}