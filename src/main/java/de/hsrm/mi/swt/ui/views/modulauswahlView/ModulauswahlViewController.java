package de.hsrm.mi.swt.ui.views.modulauswahlView;

import de.hsrm.mi.swt.ui.Controller;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import de.hsrm.mi.swt.ui.views.modulauswahlView.modulViewComponents.ModulauswahlViewLListCell;
import de.hsrm.mi.swt.ui.views.modulauswahlView.modulViewComponents.SemesterAnzahlListCell;
import de.hsrm.mi.swt.ui.views.speicherLadeView.SpeicherLadeViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class ModulauswahlViewController implements Controller {
    private Pane view;
    private Planverwaltung verwaltung;

    private ObservableList<Modul> auswahlModule;
    private ObservableList<Integer> semesterAnzahl;

    public ListView<Modul> studiengangListView;
    private ListView<Integer> semesterAuswahlView;
    private Pane speicherLadeView;

    private PlanerSceneController scene;

    private Button showAll;

    public ModulauswahlViewController (Planverwaltung verwaltung, PlanerSceneController scene){
        this.verwaltung = verwaltung;
        this.scene = scene;

        //Modulanzeige
        studiengangListView = new ListView<>();

        auswahlModule = FXCollections.observableList(verwaltung.getAuszuwaehlendeModule());
        semesterAnzahl = FXCollections.observableList(verwaltung.semesterZahlen());

        studiengangListView.setItems(auswahlModule);
        studiengangListView.setEditable(true);
        studiengangListView.setCellFactory( e -> new ModulauswahlViewLListCell(scene));
        studiengangListView.setPrefWidth(320);
        //Semesteranzeige
        showAll = new Button("Alle Module");
        VBox semAuswahlBox = new VBox();

        semesterAuswahlView = new ListView<Integer>();
        semesterAuswahlView.setItems(semesterAnzahl);
        semesterAuswahlView.setCellFactory(e -> new SemesterAnzahlListCell(verwaltung, this));

        semAuswahlBox.getChildren().addAll(showAll, semesterAuswahlView);
        speicherLadeView = new SpeicherLadeViewController(verwaltung).getRootView();
        semesterAuswahlView.setPrefWidth(120);

        // VBox semAuswahl = new VBox();
        // for(int i = 0; i<= verwaltung.semesterZahlen().size(); i++){

        //     semAuswahl.getChildren().add(new Button("Semester " + (i + 1)));
            
            
        // }
        //erstelle neue view mit den einzelnen Pane-Abschnitten (Left, right, bottom)
        view = new ModulauswahlView(studiengangListView, semAuswahlBox, speicherLadeView);
        init();
    }


    @Override
    public void init() {
        
        
        studiengangListView.setOnDragDropped((DragEvent e)->{
            Dragboard dragboard = e.getDragboard();
            
            if(dragboard.hasString()){
                verwaltung.verschiebeModulinAuswahlListe(dragboard.getString());
                updateModulListView();
            }
        });
        studiengangListView.setOnDragDone(event->{
            //System.out.println("Dragdone--");
            updateModulListView();
        });

        showAll.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            verwaltung.filterAuszuwaehlendeModule(0);
            verwaltung.setAuszuwaehlendeModule(verwaltung.getStudiengang().getAuszuwaelendeModule());
            updateModulListView();

        });
        verwaltung.getVerschobenProperty().addListener(event->{
            updateModulListView();
        });
        
    }

    public void updateModulListView(){
        studiengangListView.refresh();
        studiengangListView.setCellFactory( e -> new ModulauswahlViewLListCell(scene));
    }
    
    @Override
    public Pane getRootView() {
        return view;
    }


    public ObservableList<Modul> getAuswahlModule() {
        return auswahlModule;
    }

    
    
}
