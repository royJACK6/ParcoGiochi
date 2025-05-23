package it.its.modulo.java.gestioni;

import it.its.modulo.java.model.Visitatore;

import java.util.HashSet;
import java.util.Set;

public class GestioneVisitatori {
    private final Set<Visitatore> visitatori;

    public GestioneVisitatori() {
        this.visitatori = new HashSet<>();
    }

    public Set<Visitatore> getVisitatori() {
        return visitatori;
    }

    public void aggiungiVisitatore(Visitatore visitatore){
        visitatori.add(visitatore);
    }
}
