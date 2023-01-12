package de.hsrm.mi.swt.anwendungslogik.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Semesterzeit;

public class Studiengang {
    private String name;
    private String fachbereich;
    private int po;
    private List<Modul> auszuwaelendeModule;
    private Semesterzeit startzeit;
    private int regelStudienZeit;

    public Studiengang() { 
        name ="";
        fachbereich ="";
        po = 0;
        regelStudienZeit = 0;
    }


    public Studiengang(String name, String fachbereich, long po, String startzeit, long regelStudienZeit){
        this.name = name;
        this.po = (int)po;
        this.fachbereich = fachbereich; 
        
        this.regelStudienZeit = (int)regelStudienZeit;
        if(startzeit.equals("WS")){
            this.startzeit = Semesterzeit.WS;
        }
        if(startzeit.equals("SS")){
            this.startzeit = Semesterzeit.SS;
        }

        auszuwaelendeModule = new ArrayList<>(); 
    }

    public void addModul(Modul m){
        auszuwaelendeModule.add(m);
    }
    public void loescheModul(Modul m){
        auszuwaelendeModule.remove(m);
    }
    
    //ehemalig: holeNichtBelegteModule()
    public List<Modul> getAuszuwaelendeModule() {
        return auszuwaelendeModule;
    }

    //ehemalig: holeNichtBelegteModule()
    //in View filtern statt diese Methode zu nutzen 
    public List<Modul> getAuszuwaelendeModule(int semszahl) {
        Predicate<Modul> bySemzahl = modul -> modul.getRegelSemsZahl() == semszahl;
        var result = auszuwaelendeModule.stream().filter(bySemzahl).collect(Collectors.toList());
        return result;
    }
    public boolean checkModulVorhanden(Modul m){
        return auszuwaelendeModule.contains(m);
    }
    public String getName() {
        return name;
    }
    public int getPo() {
        return po;
    }
    public String getFachbereich() {
        return fachbereich;
    }

    public Semesterzeit getStartzeit() {
        return startzeit;
    }

    public int getRegelStudienZeit() {
        return regelStudienZeit;
    }
    

    public void setRegelStudienZeit(int regelStudienZeit) {
        this.regelStudienZeit = regelStudienZeit;
    }

    public void setAuszuwaelendeModule(List<Modul> auszuwaelendeModule) {
        this.auszuwaelendeModule = auszuwaelendeModule;
    }

    

    
}
