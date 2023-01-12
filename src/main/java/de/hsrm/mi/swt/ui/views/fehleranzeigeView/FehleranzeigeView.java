package de.hsrm.mi.swt.ui.views.fehleranzeigeView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FehleranzeigeView extends BorderPane{
    
    Label fehlertext;

    public FehleranzeigeView(String s){
        fehlertext = new Label(s);
        fehlertext.setStyle("-fx-font-size: 30;");
        fehlertext.setAlignment(Pos.CENTER);
        fehlertext.setPadding(new Insets(50,50,50,50));
        this.setCenter(fehlertext);
    }
    
}
