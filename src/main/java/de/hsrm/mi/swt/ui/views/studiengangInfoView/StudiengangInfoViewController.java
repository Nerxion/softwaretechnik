package de.hsrm.mi.swt.ui.views.studiengangInfoView;

import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.ui.Controller;
import javafx.scene.layout.Pane;

public class StudiengangInfoViewController implements Controller {
    private Planverwaltung verwalter;
    private StudiengangInfoView infoView; 

    public StudiengangInfoViewController(Planverwaltung verwalter) {
        this.verwalter = verwalter;
        this.infoView = new StudiengangInfoView();
        infoView.cpCount.setText("0 von " + verwalter.getStudiengang().getRegelStudienZeit() * 30 + " CP geschafft");
        infoView.studiengangname.setText("Planer für " + verwalter.getStudiengang().getName()); 
        infoView.po.setText(String.valueOf("PO: " + verwalter.getStudiengang().getPo()));
        infoView.semAnzahl.setText("Semesteranzahl: " + String.valueOf(verwalter.getStudiengang().getRegelStudienZeit()));
        init();
    }

    @Override
    public void init() {
        
    }

    @Override
    public Pane getRootView() {
        return infoView;
    }
    
    public void cpBerechnen() {
        List<Semester> ausgewaehlteM = verwalter.getSemesters();
        List<Modul> auszuwaehlendeM = verwalter.getStudiengang().getAuszuwaelendeModule();
        int cpSammler = 0;
        for (Semester s: ausgewaehlteM) {
            for (Modul m: s.getModule()) {
                if (m.isBestanden()) cpSammler += m.getCp();
            }
        }
        for (Modul m: auszuwaehlendeM) {
            if (m.isBestanden()) cpSammler += m.getCp();
        }
        int cpGesamt = verwalter.getStudiengang().getRegelStudienZeit() * 30;
        infoView.cpCount.setText(cpSammler + " von " + cpGesamt + " CP geschafft");
    }
}
