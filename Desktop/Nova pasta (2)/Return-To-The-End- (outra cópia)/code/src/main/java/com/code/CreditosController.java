package com.code;

import java.io.IOException;
import javafx.fxml.FXML;

public class CreditosController {

    @FXML
    public void voltar() throws IOException {
        App.setRoot("gameplay");
    }
}