package it.edu.iisgubbio.sostituzioni;

import it.edu.iisgubbio.sostituzioni.oggetti.Sostituzione.Motivo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FinestraMotiviSostituzione {
	@FXML
    ListView<Motivo> listViewMotivi;
    
    @FXML
    void initialize() {
        for (Motivo motivo : Motivo.values()) { 
            listViewMotivi.getItems().add(motivo);
        }
    }
    
    @FXML
    private void chiudi(ActionEvent e) {
        Stage stage = (Stage) listViewMotivi.getScene().getWindow();
        stage.close();
    }
}
