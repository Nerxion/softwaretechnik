package de.hsrm.mi.swt.ui.views.modulauswahlView.modulViewComponents;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.ui.views.modulauswahlView.ModulauswahlViewController;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;


public class SemesterAnzahlListCell extends ListCell<Integer>{

    ModulauswahlViewController controller;
    Planverwaltung verwaltung;
    public SemesterAnzahlListCell(Planverwaltung verwaltung, ModulauswahlViewController controller){
        this.verwaltung = verwaltung;
        this.controller = controller;
    }

    @Override
    protected void updateItem(Integer i, boolean empty){
        Button semButton = new Button("Semester"+i);
        super.updateItem(i, empty);
        setText(null);
        setGraphic(null);
        if(i!= null){
            semButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                verwaltung.filterAuszuwaehlendeModule(i);
                controller.updateModulListView();
            });
            setGraphic(semButton);

        }

    }
    
}
