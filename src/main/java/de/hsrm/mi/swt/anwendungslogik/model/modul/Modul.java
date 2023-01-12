package de.hsrm.mi.swt.anwendungslogik.model.modul;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Modul {
    private String name;
    private Long regelSemsZahl;
    private Long cp;
    private String pruefung;
    private String kurzbeschreibung;
    private String kuerzel;
    private Long lehrveranstaltungsnummer;
    private String angebot;
    private boolean verbindlichkeit;
    private List<String> abghaengigkeiten;
    private List<String> kompetenzen; //ToDo: sollte Liste von Lerhveranstalltungen sein
    private List<Lehrveranstaltungstyp> lehrveranstaltungen;
    private BooleanProperty bestanden = new SimpleBooleanProperty();
    private BooleanProperty abhaengigkeitenfehler = new SimpleBooleanProperty();



    // Konstruktoren
    public Modul(String name, Long regelSemsZahl){
        this.name = name;
        this.regelSemsZahl = regelSemsZahl;
    }

    public Modul(String name, Long regelSemsZahl, List<String> abhaengigkeiten, boolean bestanden){ //nur zum Abhaengigkeiten testen
        this.name = name;
        this.regelSemsZahl = regelSemsZahl;
        this.abghaengigkeiten = abhaengigkeiten;
        this.bestanden.set(bestanden);
        this.abhaengigkeitenfehler.set(false);
    }

    
    public Modul(String name, Long regelSemsZahl, Long cp, List<String> abghaengigkeiten,
    List<String> kompetenzen, String kurzbeschreibung) {
        this.name = name;
        this.regelSemsZahl = regelSemsZahl;
        this.cp = cp;
        this.abghaengigkeiten = abghaengigkeiten;
        this.kurzbeschreibung = kurzbeschreibung;
        this.kompetenzen = kompetenzen;
    }

    public Modul(String name, Long regelSemsZahl, Long cp, List<String> abghaengigkeiten,
    String kurzbeschreibung, List<String> lehrveranstaltungListe) {
        this.name = name;
        this.regelSemsZahl = regelSemsZahl;
        this.cp = cp;
        this.abghaengigkeiten = abghaengigkeiten;
        this.kurzbeschreibung = kurzbeschreibung;
        lehrveranstaltungen = new ArrayList<>();
        for(String l : lehrveranstaltungListe){
            if(l.equals("VORLESUNG")){
                this.lehrveranstaltungen.add(Lehrveranstaltungstyp.VORLESUNG);
            }
            if(l.equals("PRAKTIKUM")){
                this.lehrveranstaltungen.add(Lehrveranstaltungstyp.PRAKTIKUM);
            }
        }
    }


    public Modul(String titel, String kuerzel, Long lehrveranstaltungsnummer, String angebot, String pruefung,
    List<String> lehrveranstaltungListe, Long fachsemester, Long creditpoints,
    List<String> abhaengigkeitenListe, List<String> kompetenzenListe, String kurzbeschreibung) {
        this.name = titel;
        this.kuerzel = kuerzel;
        this.lehrveranstaltungsnummer = lehrveranstaltungsnummer;
        this.angebot = angebot;
        this.pruefung = pruefung;
        this.regelSemsZahl = fachsemester;
        this.cp = creditpoints;
        this.abghaengigkeiten = abhaengigkeitenListe;
        this.kompetenzen = kompetenzenListe;
        this.kurzbeschreibung = kurzbeschreibung;

        lehrveranstaltungen = new ArrayList<>();
        for(String l : lehrveranstaltungListe){
            if(l.equals("VORLESUNG")){
                this.lehrveranstaltungen.add(Lehrveranstaltungstyp.VORLESUNG);
            }
            if(l.equals("PRAKTIKUM")){
                this.lehrveranstaltungen.add(Lehrveranstaltungstyp.PRAKTIKUM);
            }
        }
    }

    //Getter
    public String getName() {
        return name;
    }

    public Long getRegelSemsZahl() {
        return regelSemsZahl;
    }

    public Long getCp() {
        return cp;
    }

    public boolean isBestanden() {
        return bestanden.get();
    }

    public BooleanProperty isBestandenProperty() {
        return bestanden;
    }

    public List<String> getAbghaengigkeiten() {
        return abghaengigkeiten;
    }

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public List<Lehrveranstaltungstyp> getLehrveranstaltungen() {
        return lehrveranstaltungen;
    }

    public String getPruefung() {
        return pruefung;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public Long getLehrveranstaltungsnummer() {
        return lehrveranstaltungsnummer;
    }

    public String getAngebot() {
        return angebot;
    }

    public boolean isVerbindlichkeit() {
        return verbindlichkeit;
    }

    public List<String> getKompetenzen() {
        return kompetenzen;
    }
    public BooleanProperty getAbhaengigkeitenfehlerProperty() {
        return abhaengigkeitenfehler;
    }

    public boolean getAbhaengigkeitenfehler() {
        return abhaengigkeitenfehler.get();
    }


    //Setter
    public void setBestanden(boolean bestanden) {
        this.bestanden.set(bestanden);
    }

    public void setAbhaengigkeitenfehler(boolean abhaengigkeitenfehler) {
        this.abhaengigkeitenfehler.set(abhaengigkeitenfehler);
    }
    
    
    
}
