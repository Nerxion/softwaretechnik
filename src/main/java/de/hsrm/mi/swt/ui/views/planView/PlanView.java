package de.hsrm.mi.swt.ui.views.planView;

import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlanView extends BorderPane{

    Button addSemesterbutton;
    Button delSemesterbutton;
    ListView<Semester> semView;

    public PlanView(ListView<Semester> semView){
        this.semView = semView;
        addSemesterbutton = new Button("Semester hinzufuegen");
        delSemesterbutton = new Button("Letztes Semester loeschen");
        //addSemesterbutton.setId("Button");
        HBox buttonbox = new HBox();
        buttonbox.setStyle("-fx-padding: 10;");
        buttonbox.getChildren().addAll(addSemesterbutton, delSemesterbutton);
        this.setCenter(semView);
        this.setTop(buttonbox);
    }
    public ListView<Semester> getList(){
        return semView; 
    }
}
