package de.hsrm.mi.swt.ui.views.modulinfoView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ModulInfoView extends StackPane {
    CheckBox bestandenCheckBox;
    Button x;
    Label modulname;
    Label kuerzel;
    Label regelSemZahl;
    Label cp;
    Label kurzbeschreibung;
    Label lehrveranstaltungen;
    Label kompetenzen;
    Label abhaengigkeiten;

    public ModulInfoView() {

        VBox vbox = new VBox();
        modulname = new Label();
        modulname.setStyle("-fx-font-size: 17;");
        kuerzel = new Label();
        regelSemZahl = new Label();
        cp = new Label();
        kurzbeschreibung = new Label();
        kurzbeschreibung.setWrapText(true);
        lehrveranstaltungen = new Label();
        kompetenzen = new Label();
        kompetenzen.setWrapText(true);
        abhaengigkeiten = new Label();
        abhaengigkeiten.setWrapText(true);

        bestandenCheckBox = new CheckBox("bestanden");

        x = new Button("X");
        HBox hbox = new HBox(x);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        vbox.getChildren().addAll(hbox, modulname, bestandenCheckBox, kuerzel, regelSemZahl, cp, kurzbeschreibung, kompetenzen, lehrveranstaltungen, abhaengigkeiten);

        this.getChildren().addAll(vbox);
    }
}
