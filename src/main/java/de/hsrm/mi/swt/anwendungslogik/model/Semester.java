package de.hsrm.mi.swt.anwendungslogik.model;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class Semester implements Comparable<Semester>{
    private int SemsZahl;
    private List<Modul> ausgewealteModule;

    public Semester(int SemsZahl){
        this.SemsZahl = SemsZahl+1;
        ausgewealteModule = new ArrayList<>();

    }

    public int getSemsZahl() {
        return SemsZahl;
    }

    public List<Modul> getModule() {
        return ausgewealteModule;
    }

    public void addModul(Modul m){
        ausgewealteModule.add(m);
    }

    public void loescheModul(Modul m){
        ausgewealteModule.remove(m);
    }

    public boolean checkModulVorhanden(Modul m){
        return ausgewealteModule.contains(m);
    }

    @Override
    public int compareTo(Semester o) {
        return Integer.compare(this.SemsZahl, o.SemsZahl);
    }
}
