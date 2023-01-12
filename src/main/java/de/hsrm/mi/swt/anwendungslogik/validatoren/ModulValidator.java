package de.hsrm.mi.swt.anwendungslogik.validatoren;

import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public interface ModulValidator {
    
    boolean validiereModul(List<Semester> semesters, Semester s, Modul m);
}
