package de.hsrm.mi.swt.anwendungslogik.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.Fehlermessage;
import de.hsrm.mi.swt.anwendungslogik.ladenSpeichern.LadenSpeichernFromJson;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.anwendungslogik.validatoren.ModulValidator;
import de.hsrm.mi.swt.anwendungslogik.validatoren.ModulValidatorAbhaengig;
import de.hsrm.mi.swt.anwendungslogik.validatoren.ModulValidatorFortschritt;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Planverwaltung {
    
    private final int FEHLERRESETTIMER = 3000;

    private List<Semester> semesters;
    private Studiengang studiengang;
    private List<Integer> semesterZahlen;
    private LadenSpeichernFromJson lsControll;
    private ModulValidator validatorFortschritt;
    private ModulValidator abhaengigValidator;
    private List<Modul> auszuwaehlendeModule;
    private StringProperty fehlerProperty;
    private BooleanProperty verschobenProperty;
    private int ausgewaeltesSemester;

    private BooleanProperty geladenProperty;
    public Planverwaltung(){
        ausgewaeltesSemester = 0;
        semesters = new ArrayList<>();
        semesterZahlen = new ArrayList<>();
        auszuwaehlendeModule = new ArrayList<>();
        studiengang = new Studiengang();
        validatorFortschritt = new ModulValidatorFortschritt();
        abhaengigValidator = new ModulValidatorAbhaengig();
        //generateDummyData();
        fehlerProperty = new SimpleStringProperty("");
        geladenProperty = new SimpleBooleanProperty(false);
        verschobenProperty = new SimpleBooleanProperty(false);
        makeSemZahlenList();
    }

    
    public void generateDummyData(){
        Modul mathe1 = new Modul("Mathe1", 1l);
        Modul mathe2 = new Modul("mathe2", 2l, new ArrayList<String>(Arrays.asList("Mathe1")), false);
        Modul mathe3 = new Modul("mathe3",3l, new ArrayList<String>(Arrays.asList("Mathe1", "mathe2")), false);
        Modul prog1 = new Modul("prog1", 1l);
        Modul prog2 = new Modul("prog2", 2l);
        Modul prog3 = new Modul("prog5", 5l);
        Modul gest1 = new Modul("gest1", 1l);
        Modul gest2 = new Modul("gest2", 2l);
        Modul gest3 = new Modul("gest4", 4l);
        Modul ads = new Modul("ads", 2l);
        
        createSems(3);
        semesters.get(0).addModul(mathe1);
        semesters.get(1).addModul(mathe2);
        semesters.get(2).addModul(mathe3);
        semesters.get(0).addModul(prog1);
        semesters.get(1).addModul(prog2);

        studiengang.addModul(ads);
        studiengang.addModul(gest3);
        studiengang.addModul(gest2);
        studiengang.addModul(gest1);
        studiengang.addModul(prog3);
        setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule());
    }

    private void makeSemZahlenList(){
        int count = getStudiengang().getRegelStudienZeit();
        for(int i = 1; i <= count; i++){
            semesterZahlen.add(i);
        }
    }

    public void createSems(int anzahl){
        for(int i = 0; i<anzahl; i++){
            semesters.add(new Semester(i));
        }
        Collections.sort(semesters, Comparator.reverseOrder());
    }

    public void addSems(){
        Semester sems = new Semester((semesters.size()));
        //sems.addModul(new Modul("RN", 4));
        semesters.add(sems);
        Collections.sort(semesters, Comparator.reverseOrder());
    }

    public void delLastSem() {
        //int lastEleIndex = semesters.size() - 1;
        if (semesters.get(0).getModule().isEmpty()) {
            semesters.remove(0);
        }
    }

    private void validiereAlleAbhaenegigkeiten() {
        for (Semester s: semesters) {
            for (Modul m: s.getModule()) {
                m.setAbhaengigkeitenfehler(!abhaengigValidator.validiereModul(semesters, s, m));
            }
        }
    }

    public void verschiebeModulInSemester(Semester intosemester, String name){
        for(Semester s: semesters){
            for(Modul m : s.getModule()){
                if(name.equals(m.getName())){
                    if(validatorFortschritt.validiereModul(semesters, intosemester, m)){
                        s.loescheModul(m);
                        intosemester.addModul(m);
                        validiereAlleAbhaenegigkeiten();
                        //printSemester();
                        setVerschoben();
                    }
                    else{
                        fehlerdetected(Fehlermessage.fortschrittfehler);
                    }
                    break;
                }
            }
        }

        for(Modul m : studiengang.getAuszuwaelendeModule()){
            if(name.equals(m.getName())){
                if(validatorFortschritt.validiereModul(semesters, intosemester, m)){
                    studiengang.loescheModul(m);
                    intosemester.addModul(m);
                    validiereAlleAbhaenegigkeiten();
                    if(ausgewaeltesSemester <= 0){
                        setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule());
                    }else{
                        setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule(ausgewaeltesSemester));
                    }
                    setVerschoben();
                }
                else{
                    fehlerdetected(Fehlermessage.fortschrittfehler);
                }
                break;
            }
        }
    }


    public void verschiebeModulinAuswahlListe(String name){
        for(Semester s: semesters){
            for(Modul m : s.getModule()){
                if(name.equals(m.getName())){
                    s.loescheModul(m);
                    studiengang.addModul(m);
                    validiereAlleAbhaenegigkeiten();
                    setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule());
                    m.setAbhaengigkeitenfehler(false);
                    //printSemester();
                    setVerschoben();
                    break;
                }
            }
        }
    }

    public void printSemester(){
        for(Semester s: semesters){
            System.out.print(s.getSemsZahl() +" | ");
            for(Modul m : s.getModule()){
                System.out.print(m.getName()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printAuswahl(){
        System.out.print(" | ");
        for(Modul m: auszuwaehlendeModule){
            System.out.print(m.getKuerzel()+" ");
        }
        System.out.println();
    }

    private void fehlerdetected(String s){
        setfehler(s);
        Thread t = new Thread(()->{
            try {
                Thread.sleep(FEHLERRESETTIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->setfehler(""));
        });
        t.start();
    }

    public void filterAuszuwaehlendeModule(int semester){
        ausgewaeltesSemester = semester;
        setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule(semester));
    }

    public void ladeJSON(){
        lsControll = new LadenSpeichernFromJson();
        if(lsControll.read()){
            semesters.clear();
            semesters.addAll(lsControll.getSemesterListe());
            Collections.sort(semesters, Comparator.reverseOrder());

            studiengang = lsControll.getStudiengang();
            setAuszuwaehlendeModule(studiengang.getAuszuwaelendeModule());
            semesterZahlen.clear();
            makeSemZahlenList();
            //printSemester();
            setGeladen();
            //printAuswahl();
        }else{
            fehlerdetected(Fehlermessage.ladefehler);
        }
           
    }

    public void speicherJSON(){
        lsControll = new LadenSpeichernFromJson();
        if(lsControll.write()){

        }else{
            fehlerdetected(Fehlermessage.speicherfehler);
        }
    }

    public List<Semester> getSemesters() {
        Collections.sort(semesters, Comparator.reverseOrder());
        return semesters;
    }

    public List<Modul> getModulevonSemester(int i){
        return semesters.get(i).getModule();
    } 

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public List<Integer> semesterZahlen(){
        return semesterZahlen;
    }

    public List<Modul> getAuszuwaehlendeModule(){
        return auszuwaehlendeModule;
    }
    
    public void setAuszuwaehlendeModule(List<Modul> auszuwaehlendeModule) {
        this.auszuwaehlendeModule.clear();
        this.auszuwaehlendeModule.addAll(auszuwaehlendeModule);
    }

    public final String getfehler(){
        return fehlerProperty.get();
    }

    public final void setfehler(String s){
        fehlerProperty.set(s);
    }

    public StringProperty getfehlerProperty(){
        return fehlerProperty;
    }
    public final boolean isGeladen(){
        return geladenProperty.get();
    }
    public final void setGeladen(){
        geladenProperty.set(!geladenProperty.get());
    }
    public BooleanProperty getGeladeProperty(){
        return geladenProperty;
    }
    public final boolean isverschoben(){
        return verschobenProperty.get();
    }
    public final void setVerschoben(){
        verschobenProperty.set(!verschobenProperty.get());
    }
    public BooleanProperty getVerschobenProperty(){
        return verschobenProperty;
    }

}
