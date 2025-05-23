package it.its.modulo.java.gestioni;

import it.its.modulo.java.model.Biglietto;
import it.its.modulo.java.model.Visitatore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class GestioneBiglietti {

    private final Set<Biglietto> bigliettiVenduti;

    public GestioneBiglietti() {
        this.bigliettiVenduti = new HashSet<>();
    }

    public Set<Biglietto> getBigliettiVenduti() {
        return bigliettiVenduti;
    }

    public void aggiungiBiglietto(Biglietto biglietto){
        bigliettiVenduti.add(biglietto);
    }

    public boolean isValido (String codiceBiglietto){
        Biglietto bigliettoDaValidare = this.bigliettiVenduti
                .stream()
                .filter( biglietto -> biglietto.getCodiceBiglietto().equals(codiceBiglietto))
                .findFirst()
                .orElse(null);
        if (bigliettoDaValidare != null) {
            LocalDate dataOdierna =LocalDate.now();
            return dataOdierna.isEqual(bigliettoDaValidare.getDataValidita());
        }
        return false;
    }

    public Visitatore cercaVisitatore (String codiceBiglietto){
        Biglietto bigliettoVisitatore = this.bigliettiVenduti
                .stream()
                .filter( biglietto -> biglietto.getCodiceBiglietto().equals(codiceBiglietto))
                .findFirst()
                .orElse(null);
        if (bigliettoVisitatore != null) {
            LocalDate dataOdierna =LocalDate.now();
            return bigliettoVisitatore.getVisitatore();
        }
        return null;
    }
}