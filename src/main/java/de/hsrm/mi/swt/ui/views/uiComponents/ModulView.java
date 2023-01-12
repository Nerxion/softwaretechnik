package de.hsrm.mi.swt.ui.views.uiComponents;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ModulView extends VBox{
    protected Label modulname;
    VBox box;

    public ModulView(Modul m){
        modulname = new Label(m.getName());
        Label cp = new Label("CP: "+m.getCp());
        this.getChildren().addAll(modulname, cp);
        if (m.isBestanden()) {
            this.setStyle("-fx-background-color: #00ff00;");
        }
        else if (m.getAbhaengigkeitenfehler()) {
            this.setStyle("-fx-background-color: #ffff00;");
        }
        
        m.isBestandenProperty().addListener(e -> {
            if (m.isBestanden()) {
                this.setStyle("-fx-background-color: #00ff00;");
            } else {

            }
        });

        m.getAbhaengigkeitenfehlerProperty().addListener(e -> {
            if (m.getAbhaengigkeitenfehler()) {
                this.setStyle("-fx-background-color: #ffff00;");
            }
        });
    }

    public Label getModulLabel(){
        return modulname;
    }
    
}
