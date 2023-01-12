package de.hsrm.mi.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class DragAndDropTest {
    Planverwaltung verwalter = new Planverwaltung();

    @Test 
    public void testDragFromPlanauswahlIntoSemester(){
        verwalter.ladeJSON();

        Assertions.assertTrue(verwalter.getSemesters().size()>0);
        Modul modul = verwalter.getAuszuwaehlendeModule().get(0);
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), modul.getName());
        Assertions.assertTrue(verwalter.getSemesters().get(0).checkModulVorhanden(modul));
        Assertions.assertFalse(verwalter.getStudiengang().checkModulVorhanden(modul));

    }

    @Test 
    public void testDragFromSemesterIntoSemester(){
        verwalter.ladeJSON();

        //Mind 2 Semester erstellen
        if (verwalter.getSemesters().size() < 2){
            verwalter.createSems(2);
        }
        //Ob es mind 2 Semester gibt
        Assertions.assertTrue(verwalter.getSemesters().size()>=2);
        Modul modul = verwalter.getAuszuwaehlendeModule().get(0);

        //verschiebt modul in Semester 1
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), modul.getName());

        //verschiebt modul in Semester 2
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(1), modul.getName());
        Assertions.assertTrue(verwalter.getSemesters().get(1).checkModulVorhanden(modul));
        Assertions.assertFalse(verwalter.getSemesters().get(0).checkModulVorhanden(modul));
    }

    @Test
    public void testDragFromSemesterIntoPlanauswahl(){
        verwalter.ladeJSON();
        Assertions.assertTrue(verwalter.getSemesters().size()>0);

        Modul modul = verwalter.getAuszuwaehlendeModule().get(0);
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(0), modul.getName());
        
        
        //verschieben von Modul in Planauswahl
        verwalter.verschiebeModulinAuswahlListe(modul.getName());
        Assertions.assertTrue(verwalter.getStudiengang().checkModulVorhanden(modul));
        Assertions.assertFalse(verwalter.getSemesters().get(0).checkModulVorhanden(modul));
    }

    @Test
    public void testFortschrittverletzt(){
        verwalter.ladeJSON();

        if (verwalter.getSemesters().size() < 2){
            verwalter.createSems(2);
        }

        Modul erstSemModul = null;
        Modul viertSemModul = null;
        Assertions.assertTrue(verwalter.getAuszuwaehlendeModule().size() != 0);
        for(Modul m : verwalter.getAuszuwaehlendeModule()){
            if(erstSemModul!= null && viertSemModul != null){
                break;
            }
            if(m.getRegelSemsZahl() == 1){
                erstSemModul = m;
                
            }
            else if(m.getRegelSemsZahl() == 4 ){
                viertSemModul = m;
            }
        }

        Assertions.assertNotNull(erstSemModul);
        Assertions.assertNotNull(viertSemModul);
        

        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(2), viertSemModul.getName());
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(2), erstSemModul.getName());


        Assertions.assertTrue(verwalter.getSemesters().get(2).checkModulVorhanden(viertSemModul));
        Assertions.assertFalse(verwalter.getSemesters().get(2).checkModulVorhanden(erstSemModul));
        Assertions.assertTrue(verwalter.getStudiengang().checkModulVorhanden(erstSemModul));
        
        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(1), erstSemModul.getName());
        Assertions.assertFalse(verwalter.getSemesters().get(1).checkModulVorhanden(erstSemModul));
        Assertions.assertTrue(verwalter.getStudiengang().checkModulVorhanden(erstSemModul));

        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(3), erstSemModul.getName());
        Assertions.assertTrue(verwalter.getSemesters().get(3).checkModulVorhanden(erstSemModul));

        verwalter.verschiebeModulInSemester(verwalter.getSemesters().get(3), viertSemModul.getName());
        Assertions.assertFalse(verwalter.getSemesters().get(3).checkModulVorhanden(viertSemModul));
    }

}
