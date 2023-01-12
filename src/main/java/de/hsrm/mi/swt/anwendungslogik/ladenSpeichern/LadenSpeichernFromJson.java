package de.hsrm.mi.swt.anwendungslogik.ladenSpeichern;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.Studiengang;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Pruefungsmodus;

public class LadenSpeichernFromJson implements LadenSpeicher {

    public boolean write() {
        JSONObject obj = new JSONObject();
        
        boolean hasNoErrors = true;

        obj.put("titel", "Softwaretechnik");
        obj.put("kuerzel", "SWT");
        obj.put("kurzbeschreibung","Die Faehigkeit zur Auswahl, Bewertung und praktischen Anwendung von Konzepten und Methoden zur systematischen Entwicklung (grosser) Softwaresystemen stellt eine zentrale Qualifikation fuer Medieninformatikerinnen und Medieninformatiker dar. Dabei sind die Phasen Analyse / Design von grundlegender Bedeutung fuer das Gelingen eines Softwareprojekts");
        obj.put("lehrveranstaltungsnummer", 4140);
        obj.put("fachsemester", 4);
        obj.put("angebot", "SS");
        obj.put("creditpoints", 6);
        obj.put("verbindlichkeit", true);
        obj.put("pruefung", Pruefungsmodus.SCHRIFTLICH.toString());
        obj.put("bestanden", true);

        JSONArray abhaengigkeitenListe = new JSONArray();
        abhaengigkeitenListe.add("Programmieren 3");
        abhaengigkeitenListe.add("Datenbanksysteme");
        abhaengigkeitenListe.add("Entwicklung interaktiver Benutzungsoberflaechen");
        abhaengigkeitenListe.add("Programmieren 2");

        obj.put("abhaengigkeiten", abhaengigkeitenListe);

        JSONArray kompetenzenListe = new JSONArray();
        kompetenzenListe.add("Software im Team entwicklen unter Einsatz entsprechender Vorgehensmodelle");
        kompetenzenListe.add("Modellieren von Anwendungsproblemen und Loesungskonzepten unter Einsatz der Unified Modeling Language (UML)");
        kompetenzenListe.add("Systematische Erhebung, Modellierung und Dokumentation von Anforderungen");
        kompetenzenListe.add("Ableitung einer und Beschreibung einer adaequaten Software-Architektur unter Beruecksichtigung grundlegender Qualitaets-Eigenschaften");
        kompetenzenListe.add("Ueberblick ueber und zielgerichteter Einsatz von aktuellen Architektur- und Entwurfsmustern");
        kompetenzenListe.add("Einsatz von Softwarewerkzeugen zur Unterstuetzung des kompletten Softwarelebenszyklus");

        obj.put("kompetenzen", kompetenzenListe);

        JSONArray lehrveranstaltungListe = new JSONArray();
        lehrveranstaltungListe.add("VORLESUNG");
        lehrveranstaltungListe.add("PRAKTIKUM");

        obj.put("lehrveranstaltung", lehrveranstaltungListe);

        JSONObject modulEins = new JSONObject();
        modulEins.put("modul", obj);

        // Modul 2 ... Modul Z

        JSONArray modulListe = new JSONArray();
        modulListe.add(modulEins);

        try (FileWriter file = new FileWriter("src/main/java/de/hsrm/mi/swt/anwendungslogik/ladenSpeichern/files/SemesterplanMedieninformatikDelete.json")) {
            file.write(modulListe.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            hasNoErrors = false;
        }
        return hasNoErrors;

    }

    private List<Modul> ausgewaehltnListe = new ArrayList<>();
    private List<Semester> semesterListe = new ArrayList<>();
    private Studiengang studiengang;
    public boolean read() {

        boolean hasNoErrors = true;

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(
                "src/main/java/de/hsrm/mi/swt/anwendungslogik/ladenSpeichern/files/SemesterplanMedieninformatik.json")) {

            //JSONObject jsonObject = (JSONObject) parser.parse(reader);
            Object object = parser.parse(reader);
            JSONArray jsonListe = (JSONArray) object;
            JSONObject studiengangteil = (JSONObject) jsonListe.get(0);
            JSONObject studiengangJson = (JSONObject) studiengangteil.get("studiengang");
             
            String name =  (String) studiengangJson.get("name");
            String fachbereich =  (String) studiengangJson.get("fachbereich");
            Long po =  (Long) studiengangJson.get("po");
            String startzeit =  (String) studiengangJson.get("startzeit");
            Long regelstudienzeit =  (Long) studiengangJson.get("regelstudienzeit");
            
            studiengang = new Studiengang(name, fachbereich, po, startzeit, regelstudienzeit);

            // System.out.println(modulListe);
            //System.out.println("ich bin hier");

            JSONArray modulListe = (JSONArray) studiengangJson.get("module");
            modulListe.forEach(mod -> parseModulObject((JSONObject) mod));

            /*System.out.println("ausgewaehltnListe: ");
            ausgewaehltnListe.forEach(System.out::println);

            System.out.println("nichtAusgewaehltnListe: ");
            semesterListe.forEach(System.out::println);
            */
            studiengang.setAuszuwaelendeModule(ausgewaehltnListe);

        } catch (FileNotFoundException e) {
            hasNoErrors = false;
            e.printStackTrace();
        } catch (IOException e) {
            hasNoErrors = false;
            e.printStackTrace();
        } catch (ParseException e) {
            hasNoErrors = false;
            e.printStackTrace();
        }
        return hasNoErrors;
    }

    private void parseModulObject(JSONObject obj) {
        Modul modulNeu;

        JSONObject jsonObject = (JSONObject) obj.get("modul");

        String titel = (String) jsonObject.get("titel");
        Long ausgewaehlt = (Long) jsonObject.get("ausgewaehlt");
        String kuerzel = (String) jsonObject.get("kuerzel");
        String kurzbeschreibung = (String) jsonObject.get("kurzbeschreibung");
        Long lehrveranstaltungsnummer = (Long) jsonObject.get("lehrveranstaltungsnummer");
        Long fachsemester = (Long) jsonObject.get("fachsemester");
        String angebot = (String) jsonObject.get("angebot");
        Long creditpoints = (Long) jsonObject.get("creditpoints");
        //boolean verbindlichkeit = (boolean) jsonObject.get("verbindlichkeit");
        boolean bestanden = (boolean) jsonObject.get("bestanden");
        String pruefung = (String) jsonObject.get("pruefung"); // convert String to enum SCHRIFTLICh ...
        pruefung.valueOf(pruefung);

        JSONArray abhaengigkeiten = (JSONArray) jsonObject.get("abhaengigkeiten");
        List<String> abhaengigkeitenListe = new ArrayList<>();
        if (abhaengigkeiten != null) {
            Iterator<String> abhaengigkeitenIterator = abhaengigkeiten.iterator();
            while (abhaengigkeitenIterator.hasNext()) {
                abhaengigkeitenListe.add(abhaengigkeitenIterator.next());
            }
        }

        JSONArray kompetenzen = (JSONArray) jsonObject.get("kompetenzen");
        List<String> kompetenzenListe = new ArrayList<>();
        if (kompetenzen != null) {
            Iterator<String> kompetenzenIterator = kompetenzen.iterator();
            while (kompetenzenIterator.hasNext()) {
                kompetenzenListe.add(kompetenzenIterator.next());
            }
        }

        // Muss noch zu Lehrveranstaltungstyp werden
        JSONArray lehrveranstaltung = (JSONArray) jsonObject.get("lehrveranstaltung");
        List<String> lehrveranstaltungListe = new ArrayList<>();
        if (lehrveranstaltung != null) {
            Iterator<String> lehrveranstaltungIterator = lehrveranstaltung.iterator();
            while (lehrveranstaltungIterator.hasNext()) {
                lehrveranstaltungListe.add(lehrveranstaltungIterator.next());
            }
        }

        // System.out.println(titel + "\n" + fachsemester + "\n" + kuerzel + "\n" +
        // kurzbeschreibung + "\n"
        // + lehrveranstaltungsnummer + "\n" + angebot + "\n" + creditpoints + "\n" +
        // verbindlichkeit + "\n" + bestanden + "\n" + pruefung);
        // System.out.println(abhaengigkeitenListe.toString());
        // System.out.println(kompetenzenListe.toString());
        // System.out.println(lehrveranstaltungListe.toString());

        modulNeu = new Modul(titel, kuerzel, lehrveranstaltungsnummer, angebot, pruefung,
                lehrveranstaltungListe, fachsemester, creditpoints, abhaengigkeitenListe, kompetenzenListe, kurzbeschreibung);

        if (ausgewaehlt == 0) {
            fuelleAusgewaehltnListe(modulNeu);
        } else {
            if (semesterListe.size() >= ausgewaehlt) {
                semesterListe.get((int)(ausgewaehlt - 1)).addModul(modulNeu);
            } else {
                while (ausgewaehlt > semesterListe.size()) {
                    Semester sems = new Semester(semesterListe.size());
                    semesterListe.add(sems);
                }
                semesterListe.get((int)(ausgewaehlt - 1)).addModul(modulNeu);
            }
           
        }
        while(semesterListe.size() < studiengang.getRegelStudienZeit()){
            semesterListe.add(new Semester((semesterListe.size())));
        }

    }

    public List<Modul> getAusgewaehltnListe() {
        return ausgewaehltnListe;
    }

    public void setAusgewaehltnListe(List<Modul> ausgewaehltnListe) {
        this.ausgewaehltnListe = ausgewaehltnListe;
    }

    public List<Semester> getSemesterListe() {
        return semesterListe;
    }

    public void setSemesterListe(List<Semester> semesterListe) {
        this.semesterListe = semesterListe;
    }

    private List<Modul> fuelleAusgewaehltnListe(Modul modul) {
        ausgewaehltnListe.add(modul);
        return ausgewaehltnListe;
    }

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(Studiengang studiengang) {
        this.studiengang = studiengang;
    }

    

}
