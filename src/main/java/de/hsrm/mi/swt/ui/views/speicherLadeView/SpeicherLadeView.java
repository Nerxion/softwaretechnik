package de.hsrm.mi.swt.ui.views.speicherLadeView;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SpeicherLadeView extends HBox {

    public Button ladenButton;
    public Button speichernButton;

    public SpeicherLadeView() {
        ladenButton = new Button("Lade Plan");
        speichernButton = new Button("Speicher Plan");
        this.getChildren().addAll(ladenButton, speichernButton);
    }
}
