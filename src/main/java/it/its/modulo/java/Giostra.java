package it.its.modulo.java;

import java.util.Objects;

public class Giostra extends Attrazione{
    private final int durataGiroMinuti;
    private final Integer limiteEta;

    public Giostra(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta,AreaTematica areaTematica, int durataGiroMinuti, Integer limiteEta) {
        super(nomeAttrazione, codiceAttrazione, tipoAttrazione, capacitaOraria, altezzaMinimaCm, aperta, areaTematica);
        this.durataGiroMinuti = durataGiroMinuti;
        this.limiteEta = limiteEta;
    }

    public int getDurataGiroMinuti() {
        return durataGiroMinuti;
    }

    public Integer getLimiteEta() {
        return limiteEta;
    }

    @Override
    protected String getDescrzioneCompleta() {
        return this.getNomeAttrazione() + " (" + this.getTipoAttrazione() + ")";
    }

    @Override
    protected int calcolaTempoAttesaMedioU(int personeInCoda) {
        return durataGiroMinuti * personeInCoda / this.getCapacitaOraria();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Giostra giostra)) return false;

        return Objects.equals(durataGiroMinuti, giostra.durataGiroMinuti) && Objects.equals(limiteEta, giostra.limiteEta);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(durataGiroMinuti);
        result = 31 * result + Objects.hashCode(limiteEta);
        return result;
    }

    @Override
    public String toString() {
        return "Attrazione{" +
                "nomeAttrazione='" + this.getNomeAttrazione() + '\'' +
                ", codiceAttrazione='" + this.getCodiceAttrazione() + '\'' +
                ", tipoAttrazione=" + this.getTipoAttrazione() +
                ", capacitaOraria=" + this.getCapacitaOraria() +
                ", altezzaMinimaCm=" + this.getAltezzaMinimaCm() +
                ", aperta=" + this.isAperta() +
                ", areaTematica=" + this.getAreaTematica() +
                ", durataGiostreMinuti='" + durataGiroMinuti + '\'' +
                ", limiteEta=" + limiteEta +
                '}';
    }
}
