package de.hsrm.mi.swt.ui.views.modulauswahlView;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ModulauswahlView extends BorderPane{
    public ModulauswahlView(ListView<Modul> studiengangView, VBox semAuswahlBox, Pane speicherLadeView){

        this.setCenter(studiengangView);
        this.setRight(semAuswahlBox);
        this.setBottom(speicherLadeView);
    }
}
