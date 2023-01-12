package de.hsrm.mi.swt.ui.scenes.planerScene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PlanerScene extends BorderPane{
    

    public PlanerScene(Pane planView, Pane modulAuswahlView, Pane studiengangInfoView){
        this.setCenter(planView);
        this.setRight(modulAuswahlView);
        this.setTop(studiengangInfoView);
    }
}
