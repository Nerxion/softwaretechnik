package de.hsrm.mi.swt.anwendungslogik.validatoren;

import java.util.List;

import de.hsrm.mi.swt.anwendungslogik.model.Semester;
import de.hsrm.mi.swt.anwendungslogik.model.modul.Modul;

public class ModulValidatorFortschritt implements ModulValidator{

    @Override
    public boolean validiereModul(List<Semester> semesters, Semester s, Modul m) {
        
        //fall 1:  modul ist auf höhe oder höher als modul das 3 sems weiter ist
        //fall 2:  modul ist nicht auf höhe oder niedriger als modul das 3 sems unter ist
        
        //System.out.println("--unten--");
        for(int i = (semesters.size() - s.getSemsZahl()); i < semesters.size()-1; i++){
            //System.out.print("i: "+ i);
            
            for(Modul mod: semesters.get(i).getModule()){
                //System.out.print(mod.getName()+ " ");
                //System.out.print(" | " + m.getName() +" + "+ mod.getName() +" -- "+ (m.getRegelSemsZahl() - mod.getRegelSemsZahl()));
                if(m.getRegelSemsZahl() - mod.getRegelSemsZahl() <= -3){
                    //System.out.println(m.getName() +" wird von "+ mod.getName() +" gehindert -- "+ (m.getRegelSemsZahl() - mod.getRegelSemsZahl()));
                    return false;
                }
            }
            
            //System.out.println();
        }
        //System.out.println("--oben--");
        //System.out.println(semesters.size() - s.getSemsZahl());
        for(int i = 0; i < (semesters.size() - s.getSemsZahl())+1; i++){
            //System.out.print("|  i: "+ i);
            for(Modul mod: semesters.get(i).getModule()){
                //System.out.print(" | " + m.getName() +" + "+ mod.getName() +" -- "+ (m.getRegelSemsZahl() - mod.getRegelSemsZahl()));
                
                if(m.getRegelSemsZahl() - mod.getRegelSemsZahl() >= 3){
                    //System.out.println(m.getName() +" wird von "+ mod.getName() +" gehindert -- "+ (m.getRegelSemsZahl() - mod.getRegelSemsZahl()));
                    return false;
                }
                
            }
            
            //System.out.println();
        }

        /*
        for(Semester sems: semesters){
            for(Modul mod : sems.getModule()){
                if(m.getRegelSemsZahl() - mod.getRegelSemsZahl() >= 3 || m.getRegelSemsZahl() - mod.getRegelSemsZahl() <= -3){
                    System.out.println(m.getName() +" wird von "+ mod.getName() +" gehindert -- "+ (m.getRegelSemsZahl() - mod.getRegelSemsZahl()));
                    return false;
                }
            }
        }
        */
        return true;
    }
    
}
