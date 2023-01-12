package de.hsrm.mi.swt.ui.views.studiengangInfoView;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StudiengangInfoView extends StackPane {
    public Label cpCount;
    public Label studiengangname;
    public Label po;
    public Label semAnzahl;

    public StudiengangInfoView() {
        VBox vbox = new VBox();
        
        cpCount = new Label();
        studiengangname = new Label();
        studiengangname.setStyle("-fx-font-size: 40;"); 
        po = new Label();
        semAnzahl = new Label();

        vbox.getChildren().addAll(studiengangname, po, semAnzahl, cpCount);

        this.getChildren().add(vbox);
    }


}
