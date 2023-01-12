package de.hsrm.mi.swt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;

public class SemAnzahlTest {
    Planverwaltung verwalter = new Planverwaltung();
    
    @Test
    public void testSemHinzufuegen() {
        verwalter.ladeJSON();

        Assertions.assertTrue(verwalter.getSemesters().size()==7);
        verwalter.addSems();
        Assertions.assertTrue(verwalter.getSemesters().size()==8); // 1 Sem mehr
    }

    @Test
    public void testSemLoeschen() {
        verwalter.ladeJSON();

        Assertions.assertTrue(verwalter.getSemesters().size()==7);
        verwalter.delLastSem();
        Assertions.assertTrue(verwalter.getSemesters().size()==6); // 1 Sem weniger
    }

    @Test
    public void testSemLoeschenNurWennLeer() {
        verwalter.ladeJSON();

        Assertions.assertTrue(verwalter.getSemesters().size()==7);
        // irgend ein Modul in oberstes Sem verschieben
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), verwalter.getAuszuwaehlendeModule().get(0).getName());
        verwalter.delLastSem();
        Assertions.assertTrue(verwalter.getSemesters().size()==7); // sollte noch gleiche Anzahl sein wie vorher
    }
}
