package de.hsrm.mi.swt.ui.views.fehleranzeigeView;

import de.hsrm.mi.swt.ui.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FehleranzeigeViewController implements Controller{
    private FehleranzeigeView root;
    private String fehlerMessage;
    private Label fehlerlable;
   
    public FehleranzeigeViewController(String fehlerMessage){
        this.fehlerMessage = fehlerMessage;
        root = new FehleranzeigeView(fehlerMessage);
        fehlerlable = root.fehlertext;
    }

    @Override
    public void init(){
        
        
    }
    public void setfehlerMessage(String fehler){
        fehlerMessage = fehler;
        fehlerlable.setText(fehlerMessage);
    }

    @Override
    public Pane getRootView(){
        return root;
    }

    
}
