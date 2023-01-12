package de.hsrm.mi.swt;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import de.hsrm.mi.swt.anwendungslogik.Fehlermessage;
import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;

public class LadenSpeichernTest {
    Planverwaltung verwalter = new Planverwaltung();

    @Test
    public void testLoad(){
        verwalter.ladeJSON();
        Assertions.assertFalse(verwalter.getfehler().equals(Fehlermessage.ladefehler));
    }

    @Test
    public void testModelBefuellt(){
        
        verwalter.ladeJSON();
        Assertions.assertNotEquals(verwalter.getStudiengang().getName(),"");
        Assertions.assertNotEquals(verwalter.getStudiengang().getRegelStudienZeit(),0);
        Assertions.assertNotNull(verwalter.getAuszuwaehlendeModule());
        Assertions.assertNotNull(verwalter.getSemesters());
        Assertions.assertNotNull(verwalter.semesterZahlen());
        Assertions.assertTrue(verwalter.getAuszuwaehlendeModule().size() == verwalter.getStudiengang().getAuszuwaelendeModule().size());
        Assertions.assertTrue(verwalter.semesterZahlen().size() == verwalter.getStudiengang().getRegelStudienZeit());
    }

}
