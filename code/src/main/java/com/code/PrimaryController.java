package com.code;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void play() throws IOException{
        App.setRoot("selection");
    }
    
    @FXML
    private void saves() throws IOException{
        App.setRoot("secondary");
    }
}
