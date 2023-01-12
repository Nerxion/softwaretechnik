package de.hsrm.mi.swt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;

public class AuszuwaehlendeModuleFilternTest {
    Planverwaltung verwalter = new Planverwaltung();

    @Test
    public void testAlleModuleZuBeginn() {
        verwalter.ladeJSON();

        Assertions.assertEquals(verwalter.getAuszuwaehlendeModule(), verwalter.getStudiengang().getAuszuwaelendeModule());
    }

    @Test 
    public void testNurModuleAusEinemSem() {
        verwalter.ladeJSON();
        verwalter.filterAuszuwaehlendeModule(1);

        Assertions.assertEquals(verwalter.getAuszuwaehlendeModule(), verwalter.getStudiengang().getAuszuwaelendeModule(1));
    }

    @Test
    public void testDannWiederAlleModule() {
        verwalter.ladeJSON();
        verwalter.filterAuszuwaehlendeModule(1);
        verwalter.filterAuszuwaehlendeModule(0);
        verwalter.setAuszuwaehlendeModule(verwalter.getStudiengang().getAuszuwaelendeModule());

        Assertions.assertEquals(verwalter.getAuszuwaehlendeModule(), verwalter.getStudiengang().getAuszuwaelendeModule());
    }
}
