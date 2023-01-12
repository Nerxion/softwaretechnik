package de.hsrm.mi.swt.ui.scenes.planerScene;
import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.ui.Controller;
import de.hsrm.mi.swt.ui.views.modulinfoView.ModulInfoViewController;
import de.hsrm.mi.swt.ui.views.fehleranzeigeView.FehleranzeigeViewController;
import de.hsrm.mi.swt.ui.views.modulauswahlView.ModulauswahlViewController;
import de.hsrm.mi.swt.ui.views.planView.PlanViewController;
import de.hsrm.mi.swt.ui.views.studiengangInfoView.StudiengangInfoViewController;
import javafx.scene.layout.Pane;

public class PlanerSceneController implements Controller{
    private Planverwaltung verwaltung;
    private PlanerScene rootScene;
    private FehleranzeigeViewController fehlercontroller;
    private StudiengangInfoViewController studieninfocontroller;
    private PlanViewController planViewController;
    private ModulauswahlViewController modulauswahlViewController;
    public PlanerSceneController(Planverwaltung verwaltung){
        this.verwaltung = verwaltung;
        this.planViewController = new PlanViewController(verwaltung, this);
        this.modulauswahlViewController = new ModulauswahlViewController(verwaltung, this);
        fehlercontroller = new FehleranzeigeViewController(verwaltung.getfehler());
        studieninfocontroller = new StudiengangInfoViewController(verwaltung);
        this.rootScene = new PlanerScene(planViewController.getRootView(), modulauswahlViewController.getRootView(), studieninfocontroller.getRootView());

        init();
    }
  
    @Override
    public void init() {
        verwaltung.getfehlerProperty().addListener(e->{
            if(verwaltung.getfehler().equals("")){
                rootScene.setTop(studieninfocontroller.getRootView());
            }else{
                fehlercontroller.setfehlerMessage(verwaltung.getfehler());
                rootScene.setTop(fehlercontroller.getRootView());
            }
        });
        verwaltung.getGeladeProperty().addListener(event->{
            this.studieninfocontroller = new StudiengangInfoViewController(verwaltung);
            
            rootScene.setCenter(new PlanViewController(verwaltung, this).getRootView());
            rootScene.setRight(new ModulauswahlViewController(verwaltung, this).getRootView());
            rootScene.setTop(studieninfocontroller.getRootView());
        });

        
        
    }

    public void showModulInfo(Modul m){
        rootScene.setBottom(new ModulInfoViewController(m, this, studieninfocontroller).getRootView());
    }

    public void noModulInfo() {
        rootScene.setBottom(null);
    }

    @Override 
    public Pane getRootView(){
        return rootScene;
    }

}
