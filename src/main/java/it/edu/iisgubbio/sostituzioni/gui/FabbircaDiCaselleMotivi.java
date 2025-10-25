package it.edu.iisgubbio.sostituzioni.gui;

import it.edu.iisgubbio.sostituzioni.oggetti.Sostituzione.Motivo;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class FabbircaDiCaselleMotivi implements Callback<ListView<Motivo>, ListCell<Motivo>>{
    @Override
    public ListCell<Motivo> call(ListView<Motivo> studentListView) {
        return new CasellaMotivo();
    }
}
