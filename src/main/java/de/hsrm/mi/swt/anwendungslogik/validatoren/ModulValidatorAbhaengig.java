package de.hsrm.mi.swt.anwendungslogik.validatoren;

import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class ModulValidatorAbhaengig implements ModulValidator{

    @Override
    public boolean validiereModul(List<Semester> semesters, Semester s, Modul m) {
        // semesters = Liste der Semester inkl Module, die im Plan rein gezogen wurden schon
        // s = Semester, in das Modul gezogen wird
        // m = Modul, das bewegt wird
        boolean alledrin = true;

        if (m.getAbghaengigkeiten() != null) {
            List<String> abhaengigkeiten = m.getAbghaengigkeiten();
            boolean[] sindrin = new boolean[m.getAbghaengigkeiten().size()];
            int i = 0;
            
            for (String modulname: abhaengigkeiten) {
                for (Semester sem: semesters) { 
                    if (sem.getSemsZahl() < s.getSemsZahl()) {
                        for (Modul mod: sem.getModule()) {
                            if (modulname.equals(mod.getName())) {
                                sindrin[i] = true;
                                i++;
                            }
                        }
                    }       
                }
            }
            for (boolean e: sindrin) {
                if (e == false) alledrin = false;
            }
        }

        return alledrin;
    }
    
}
