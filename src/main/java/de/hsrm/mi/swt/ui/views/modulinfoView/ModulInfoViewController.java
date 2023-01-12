package de.hsrm.mi.swt.ui.views.modulinfoView;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Lehrveranstaltungstyp;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.ui.Controller;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import de.hsrm.mi.swt.ui.views.studiengangInfoView.StudiengangInfoViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ModulInfoViewController implements Controller {
    private ModulInfoView view;
    private Modul m;
    private CheckBox bestandenCheckBox;
    private Button x;
    PlanerSceneController scene;
    StudiengangInfoViewController studiInfoController;
    
    public ModulInfoViewController(Modul m, PlanerSceneController scene, StudiengangInfoViewController studiInfoController) {
        this.m = m;
        this.view = new ModulInfoView();
        this.x = view.x;
        this.scene = scene;
        this.studiInfoController = studiInfoController;

        setViewContents();

        bestandenCheckBox = view.bestandenCheckBox;
        bestandenCheckBox.setSelected(m.isBestanden());

        init();
    }


    @Override
    public void init() {
        bestandenCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                m.setBestanden(newValue);
                studiInfoController.cpBerechnen();
                //System.out.println("Modul ist bestanden: " + m.isBestanden());
            }
        });
        
        x.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            scene.noModulInfo();
        });
    }

    @Override
    public Pane getRootView() {
        // TODO Auto-generated method stub
        return view;
    }

    public void setViewContents() {
        view.modulname.setText("Modulname: " + m.getName());
        view.kuerzel.setText("Kuerzel: " + m.getKuerzel());
        view.regelSemZahl.setText("Regelsemesterzahl: " + String.valueOf(m.getRegelSemsZahl()));
        view.cp.setText("CP: " + String.valueOf(m.getCp()));
        view.kurzbeschreibung.setText("Kurzbeschreibung: " + m.getKurzbeschreibung());

        String lehrveranstStr = "";
        for (Lehrveranstaltungstyp lehrveranst: m.getLehrveranstaltungen()) {
            if (lehrveranstStr != "") lehrveranstStr = lehrveranstStr + ", ";
            lehrveranstStr = lehrveranstStr + lehrveranst.toString().substring(0, 1) + lehrveranst.toString().substring(1).toLowerCase();
        }
        view.lehrveranstaltungen.setText("Lehrveranstaltung(en): " + lehrveranstStr);

        String kompetenzStr = "";
        for (String kompetenz: m.getKompetenzen()) {
            if (kompetenzStr != "") kompetenzStr = kompetenzStr + ", ";
            kompetenzStr = kompetenzStr + kompetenz;
        }
        view.kompetenzen.setText("Kompetenz(en): " + kompetenzStr);

        String abhaengigkeitenStr = "";
        for (String abhaengigkeit: m.getAbghaengigkeiten()) {
            if (abhaengigkeitenStr != "") abhaengigkeitenStr = abhaengigkeitenStr + ", ";
            abhaengigkeitenStr = abhaengigkeitenStr + abhaengigkeit;
        }
        view.abhaengigkeiten.setText("Empfohlene fachliche Voraussetzung(en): " + abhaengigkeitenStr);
    }
    
}
