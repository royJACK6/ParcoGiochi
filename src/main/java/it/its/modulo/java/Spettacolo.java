package it.its.modulo.java;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Spettacolo extends Attrazione{
    private final List<LocalTime> orariInizio;
    private final int durataSpettacoloInMinuti;
    private final int postiASedere;

    public Spettacolo(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta, AreaTematica areaTematica, List<LocalTime> orariInizio, int durataSpettacoloInMinuti, int postiASedere) {
        super(nomeAttrazione, codiceAttrazione, tipoAttrazione, capacitaOraria, altezzaMinimaCm, aperta, areaTematica);
        if (orariInizio != null) {
            orariInizio.sort(LocalTime::compareTo);
            this.orariInizio = orariInizio;
        } else this.orariInizio = new ArrayList<>();
        this.durataSpettacoloInMinuti = durataSpettacoloInMinuti;
        this.postiASedere = postiASedere;
    }

    @Override
    public String getDescrzioneCompleta() {
        LocalTime oraAttuale = LocalTime.now();
        Optional<LocalTime> prossimoSpettacolo= this.orariInizio
                .stream()
                .filter(orariInizio -> orariInizio.isAfter(oraAttuale)
                        || orariInizio.equals(oraAttuale))
                .findFirst();
        return prossimoSpettacolo
                .map(localTime -> this.getNomeAttrazione() + "Ora Inizio: " + localTime)
                .orElseGet(() -> this.getNomeAttrazione() + "Ora Inizio: " + (!this.orariInizio.isEmpty()
                        ? this.orariInizio.getFirst()
                        :"NON CI SONO ORARI DISPONIBILI"));
    }

    @Override
    public int calcolaTempoAttesaMedioU(int personeInCoda) {
        int prossimoSpettacoloDisponibile = personeInCoda / this.postiASedere;
        LocalTime orarioAttuale = LocalTime.now();
        List<LocalTime> prossimoSpettacolo = this.orariInizio
                .stream()
                .filter(orarioInizio -> orarioInizio.isAfter(orarioAttuale)
                        || orarioInizio.equals(orarioAttuale))
                .toList();
        if (!prossimoSpettacolo.isEmpty()
                && prossimoSpettacolo.size() > prossimoSpettacoloDisponibile) {
            LocalTime orarioProssimoSpettacolo = prossimoSpettacolo.get(prossimoSpettacoloDisponibile);
            Duration duration = Duration.between(orarioAttuale, orarioProssimoSpettacolo);
            return (int) duration.get(ChronoUnit.HOURS);
        }
        return 1;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Spettacolo that)) return false;
        if (!super.equals(o)) return false;

        return durataSpettacoloInMinuti == that.durataSpettacoloInMinuti && postiASedere == that.postiASedere && Objects.equals(orariInizio, that.orariInizio);
    }

    public List<LocalTime> getOrariInizio() {
        return orariInizio;
    }

    public int getDurataSpettacoloInMinuti() {
        return durataSpettacoloInMinuti;
    }

    public int getPostiASedere() {
        return postiASedere;
    }

    @Override
    public String toString() {
        return "Spettacolo{" +
                "nomeAttrazione='" + this.getNomeAttrazione() + '\'' +
                ", codiceAttrazione='" + this.getCodiceAttrazione() + '\'' +
                ", tipoAttrazione=" + this.getTipoAttrazione() +
                ", capacitaOraria=" + this.getCapacitaOraria() +
                ", altezzaMinimaCm=" + this.getAltezzaMinimaCm() +
                ", aperta=" + this.isAperta() +
                ", areaTematica=" + this.getAreaTematica() +
                ", orariInizio=" + orariInizio +
                ", durataSpettacoloInMinuti=" + durataSpettacoloInMinuti +
                ", postiASedere=" + postiASedere +
                '}';
    }
}
