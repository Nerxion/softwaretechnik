package de.hsrm.mi.swt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;

public class CPTest {
    Planverwaltung verwalter = new Planverwaltung();

    @Test
    public void testGesamtCP() {
        Assertions.assertEquals(verwalter.getStudiengang().getRegelStudienZeit() * 30, 0);
        verwalter.ladeJSON();
        Assertions.assertEquals(verwalter.getStudiengang().getRegelStudienZeit() * 30, 210);
    }
}
