/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.utils;

import java.util.Optional;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author cliente
 */
public class AlertUtils {
    public static void error(String title,Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
    
    public static void info(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static String dialog(String title, String message){
        Dialog<String> dialogo = new Dialog<>();
        dialogo.setTitle(title);
        dialogo.setHeaderText(message);

        // Configurando os botões do diálogo
        ButtonType botaoSalvar = new ButtonType("Salvar", ButtonType.OK.getButtonData());
        dialogo.getDialogPane().getButtonTypes().addAll(botaoSalvar, ButtonType.CANCEL);

        // Criando o campo de texto no layout do diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome");
        grid.add(campoNome, 0, 0);

        dialogo.getDialogPane().setContent(grid);

        // Converte o resultado para a string digitada quando o botão salvar for clicado
        dialogo.setResultConverter(dialogButton -> {
            if (dialogButton == botaoSalvar) {
                return campoNome.getText();
            }
            return null;
        });
        final String[] nomeResp = {""};

        Optional<String> resultado = dialogo.showAndWait();
        resultado.ifPresent(nome -> {
            nomeResp[0] = nome;
        });

        return nomeResp[0];
    }
}
