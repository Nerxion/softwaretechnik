package de.hsrm.mi.swt.ui.views.planView.planViewComponents;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.ui.Controller;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class ListRowController implements Controller{
    private Pane rootView;
    public static ListView<Modul> viewModule;
    private ObservableList<Modul> module;
    private Planverwaltung verwaltung;
    private Label semesterZahl;
    private Semester s;
    //private boolean dragDone;
    public ListRowController(Semester s, Planverwaltung verwaltung, PlanerSceneController scene) {
        this.verwaltung = verwaltung;
        this.s = s;
        //dragDone = false;

        semesterZahl = new Label();
        
        semesterZahl.setText(""+s.getSemsZahl());
     

        module = FXCollections.observableList(s.getModule());
        viewModule = new ListView<Modul>();
        viewModule.setItems(module);
        viewModule.setOrientation(Orientation.HORIZONTAL);
        viewModule.setCellFactory(e-> new ListRowCell(scene));
        //viewModule.setMaxHeight(60);
        //viewModule.setMinWidth(500);
        rootView = new ListRow(semesterZahl, viewModule,s, verwaltung);

        init();
    }

    
    @Override
    public void init() {
        
        viewModule.setOnDragOver(event ->
        {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasString())
            {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
           
        viewModule.setOnDragDropped((DragEvent e)->{
            Dragboard dragboard = e.getDragboard();
            
            if(dragboard.hasString()){
                verwaltung.verschiebeModulInSemester(s, dragboard.getString());
                //System.out.println("dropped");
                viewModule.refresh();
                e.setDropCompleted(true);
            }else{
                e.setDropCompleted(false);
            }

        });
    }
    

    public Pane getRootView() {
        return rootView;
    }
}
