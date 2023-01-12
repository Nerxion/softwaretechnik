package de.hsrm.mi.swt.ui.views.speicherLadeView;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.ui.Controller;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SpeicherLadeViewController implements Controller {
    private Planverwaltung verwaltung;
    private Pane view;
    private Button ladenButton;
    private Button speichernButton;

    public SpeicherLadeViewController(Planverwaltung verwaltung) {
        this.verwaltung = verwaltung;

        SpeicherLadeView speicherLadeView = new SpeicherLadeView();
        this.ladenButton = speicherLadeView.ladenButton;
        this.speichernButton = speicherLadeView.speichernButton;

        view = speicherLadeView;

        init();
    }

    @Override
    public void init() {

        ladenButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            verwaltung.ladeJSON();

        });

        speichernButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            verwaltung.speicherJSON();

        });

    }

    @Override
    public Pane getRootView() {
        return view;
    }
}
