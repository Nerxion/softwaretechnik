package de.hsrm.mi.swt.ui.views.modulauswahlView.modulViewComponents;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import de.hsrm.mi.swt.ui.views.uiComponents.ModulView;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ModulauswahlViewLListCell extends ListCell<Modul>{

    private ModulView modulView;
    private PlanerSceneController scene;
    public ModulauswahlViewLListCell(PlanerSceneController scene){
        this.scene = scene; 
    }

    @Override
    protected void updateItem(Modul m, boolean empty){
        
        super.updateItem(m, empty);
        setText(null);
        setGraphic(null);
        if(m!= null){
            modulView = new ModulView(m);
            setGraphic(modulView);

            modulView.setOnMouseClicked(e->{
                scene.showModulInfo(m);
            });
        }
        setOnDragDetected(event ->{
            if(getItem() == null){
                return;
            }
            Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(this.getItem().getName());
            dragboard.setContent(content);
            event.consume();
        });

        setOnDragOver((DragEvent event)->{
            if(event.getDragboard().hasString()&& event.getGestureSource() != this){
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }
}
