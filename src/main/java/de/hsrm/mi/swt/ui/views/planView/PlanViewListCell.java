package de.hsrm.mi.swt.ui.views.planView;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import de.hsrm.mi.swt.ui.views.planView.planViewComponents.ListRowController;
import javafx.scene.control.ListCell;

public class PlanViewListCell extends ListCell<Semester>{
    
    Planverwaltung verwaltung;
    PlanerSceneController scene;
    public PlanViewListCell(Planverwaltung verwaltung, PlanerSceneController scene){
        this.verwaltung = verwaltung;
        this.scene = scene;
    }

    @Override
    protected void updateItem(Semester s, boolean empty){
        super.updateItem(s, empty);
        setText(null);
        setText(null);
        
    
        if(s!= null){
            setGraphic(new ListRowController(s, verwaltung, scene).getRootView());
        }
    
    }

}
