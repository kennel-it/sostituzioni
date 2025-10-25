package it.edu.iisgubbio.sostituzioni.gui;

import it.edu.iisgubbio.sostituzioni.oggetti.Sostituzione.Motivo;
import javafx.scene.control.ListCell;

public class CasellaMotivo extends ListCell<Motivo>{
    
    @Override
    public void updateItem(Motivo item, boolean empty) {
        super.updateItem(item, empty);
        int index = this.getIndex();
        String name = null;
        
        if (item != null && !empty) {
            this.getStyleClass().clear();
            this.getStyleClass().add("cell");
            this.getStyleClass().add("list-cell");
            this.getStyleClass().add(item.toString().toLowerCase());
            this.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            name = (index + 1) +": "+ item.toString();
        }
         
        this.setText(name);
    }
}
