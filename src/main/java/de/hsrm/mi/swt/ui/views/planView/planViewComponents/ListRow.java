package de.hsrm.mi.swt.ui.views.planView.planViewComponents;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;

public class ListRow extends HBox{

    public ListRow(Label semesterLabel, ListView<Modul> listview, Semester s, Planverwaltung verwaltung){
        this.getChildren().setAll(semesterLabel, listview);
        
        listview.setStyle("-fx-min-width: 100em");
        listview.setMaxHeight(80);
        this.setOnDragEntered((DragEvent event)->{
            this.setStyle("-fx-background-color: red;");
            //System.out.println("listrow on Drag Entered "+ semesterLabel.getText()+ event.toString());
        });
        this.setOnDragExited((DragEvent event)->{
            this.setStyle("");
        });
        
    }
}
