package de.hsrm.mi.swt.ui.views.modulauswahlView.modulViewComponents;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SemesterAuswahlView extends VBox{

    Planverwaltung verwaltung;
    
    public SemesterAuswahlView(Planverwaltung verwaltung){
        this.verwaltung = verwaltung;
        //VBox semAuswahl = new VBox();
        for(int i = 0; i<= verwaltung.semesterZahlen().size(); i++){

            this.getChildren().add(new Button("Button " + (int)(i + 1)));
        }
        
    }

}
