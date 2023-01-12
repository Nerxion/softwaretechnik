package de.hsrm.mi.swt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class AbhaengigkeitenTest {
    Planverwaltung verwalter = new Planverwaltung();

    @Test
    public void testAbhaengigkeitverletzt() {
        verwalter.ladeJSON();
        Modul hohesModul = verwalter.getAuszuwaehlendeModule().get(14);
        
        // hohes Sem Modul reinschieben, sollte Abhaengigkeitenfehler werfen
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), hohesModul.getName());

        Assertions.assertTrue(hohesModul.getAbhaengigkeitenfehler());
    }

    @Test
    public void testAbhaengigkeitNichtMehrVerletzt() {
        verwalter.ladeJSON();
        Modul gest1 = verwalter.getAuszuwaehlendeModule().get(2);
        Modul gest2 = verwalter.getAuszuwaehlendeModule().get(8);
        
        // 2. Sem Modul reinschieben, sollte Abhaengigkeitenfehler werfen
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), gest2.getName());
        // 1. Sem Modul reinschieben, Abhaengigkeiten sollten erfüllt sein
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(1), gest1.getName());

        Assertions.assertFalse(gest2.getAbhaengigkeitenfehler());
        Assertions.assertFalse(gest1.getAbhaengigkeitenfehler());
    }

    @Test
    public void testAbhaengigkeitErneutVerletzt() {
        verwalter.ladeJSON();
        Modul gest1 = verwalter.getAuszuwaehlendeModule().get(2);
        Modul gest2 = verwalter.getAuszuwaehlendeModule().get(8);
        
        // 2. Sem Modul reinschieben, sollte Abhaengigkeitenfehler werfen
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), gest2.getName());
        // 1. Sem Modul reinschieben, Abhaengigkeiten sollten erfüllt sein
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(1), gest1.getName());
        // 1. Sem Modul wieder rausschieben, Abhaengigkeiten sollten daher wieder Fehler werfen
        verwalter.verschiebeModulinAuswahlListe(gest1.getName());

        Assertions.assertTrue(gest2.getAbhaengigkeitenfehler());
    }
}
