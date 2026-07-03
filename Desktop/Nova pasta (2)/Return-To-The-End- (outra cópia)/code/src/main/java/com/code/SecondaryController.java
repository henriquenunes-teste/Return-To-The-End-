package com.code;

import com.code.dao.SaveDAO;
import com.code.models.Save;
import com.code.utils.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController implements Initializable{
    
    private ObservableList<Save> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Save> table;
    @FXML
    private TableColumn<Save,String> colName;
    @FXML
    private TableColumn<Save,LocalDateTime> colLast;
    @FXML
    private TableColumn<Save,LocalDateTime> colCreated;
   
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void back() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void novo() throws IOException {
        App.setRoot("formulario");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            (new SaveDAO()).listAll(list);
        }catch(SQLException e){
            AlertUtils.error("Erro", e);
        }
        
        
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("last"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
        
        table.setItems(list);
    }
}