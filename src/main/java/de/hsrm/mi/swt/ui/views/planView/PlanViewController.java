package de.hsrm.mi.swt.ui.views.planView;
import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.ui.Controller;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PlanViewController implements Controller{
    private PlanView view;
    private Planverwaltung verwaltung;
    private ListView<Semester> semesterListView;
    private Button addbutton;
    private Button deletebutton;
    private ObservableList<Semester> semesterListe;
    private PlanerSceneController scene;

    public PlanViewController(Planverwaltung verwaltung, PlanerSceneController scene){
        this.verwaltung = verwaltung;
        this.scene = scene;
        semesterListView = new ListView<>();
        semesterListe = FXCollections.observableList(verwaltung.getSemesters());

        semesterListView.setItems(semesterListe);
        semesterListView.setCellFactory( e -> new PlanViewListCell(verwaltung, scene));
        
        PlanView planView = new PlanView(semesterListView);
        this.addbutton = planView.addSemesterbutton;
        this.deletebutton = planView.delSemesterbutton;

        semesterListView.setMinHeight(500);
        view = planView;

        init();
        
    }
    @Override
    public void init(){
        
        addbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            verwaltung.addSems();
            semesterListView.refresh();
            semesterListView.setCellFactory( e -> new PlanViewListCell(verwaltung, scene));
        });
        
        deletebutton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            verwaltung.delLastSem();
            semesterListView.refresh();
            semesterListView.setCellFactory( e -> new PlanViewListCell(verwaltung, scene));
        });
        verwaltung.getVerschobenProperty().addListener(event->{
            semesterListView.refresh();
            semesterListView.setCellFactory( e -> new PlanViewListCell(verwaltung, scene));
        });

    }

    @Override
    public Pane getRootView() {
        return view;
        
    }

}
