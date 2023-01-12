package de.hsrm.mi.swt.anwendungslogik.model.modul;

public class Pruefung {

    private Pruefungsmodus pruefungsmodus;
    private boolean bestanden;

    

    //Kontruktör
    public Pruefung(Pruefungsmodus pruefungsmodus, boolean bestanden) {
        this.pruefungsmodus = pruefungsmodus;
        this.bestanden = bestanden;
    }
    
    //Getter
    public Pruefungsmodus getPruefungsmodus() {
        return pruefungsmodus;
    }
    public boolean isBestanden() {
        return bestanden;
    }

    //Setter
    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }

    public void setPruefungsmodus(Pruefungsmodus pruefungsmodus) {
        this.pruefungsmodus = pruefungsmodus;
    }

    @Override
    public String toString() {
        return "Pruefung [bestanden=" + bestanden + ", pruefungsmodus=" + pruefungsmodus + "]";
    }
    

    

    
    
}
