package it.its.modulo.java;

import java.util.HashMap;
import java.util.Map;

public abstract class Attrazione {
    private final String nomeAttrazione;
    private final String codiceAttrazione;
    public enum Tipo {
        ROLLERCOASTER, SHOW, FOOD;
        public final static Map<String, Tipo> TIPO_MAP = new HashMap<>();

        static {
            TIPO_MAP.put("rollercoaster", ROLLERCOASTER);
            TIPO_MAP.put("show", SHOW);
            TIPO_MAP.put("food", FOOD);
        }
        public static Tipo lookUp(String value){
            if (value == null) throw new IllegalArgumentException("Nessun" + value);
            value = value.toLowerCase();
            Tipo tipo = TIPO_MAP.get(value);
            if (tipo != null){
                return tipo;
            } else {
                throw new IllegalArgumentException("Nessun tipo trovato per " + value);
            }
        }
    }
    private final Tipo tipoAttrazione;
    private final int capacitaOraria;
    private final int altezzaMinimaCm;
    private final boolean aperta;
    private final AreaTematica areaTematica;

    public Attrazione(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta, AreaTematica areaTematica) {
        this.nomeAttrazione = nomeAttrazione;
        this.codiceAttrazione = codiceAttrazione;
        this.tipoAttrazione = tipoAttrazione;
        this.capacitaOraria = capacitaOraria;
        this.altezzaMinimaCm = altezzaMinimaCm;
        this.aperta = aperta;
        this.areaTematica = areaTematica;
    }

    public String getNomeAttrazione() {
        return nomeAttrazione;
    }

    public String getCodiceAttrazione() {
        return codiceAttrazione;
    }

    public Tipo getTipoAttrazione() {
        return tipoAttrazione;
    }

    public int getCapacitaOraria() {
        return capacitaOraria;
    }

    public int getAltezzaMinimaCm() {
        return altezzaMinimaCm;
    }

    public boolean isAperta() {
        return aperta;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }
    protected abstract String getDescrzioneCompleta();

    protected abstract int calcolaTempoAttesaMedioU(int personeInCoda);

    @Override
    public boolean equals(Object obj) {
        if (obj ==null) return false;
        if (obj instanceof Attrazione attrazione){
            return attrazione.getCodiceAttrazione().equals(this.getCodiceAttrazione());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.codiceAttrazione.hashCode();
    }

    @Override
    public String toString() {
        return "Attrazione{" +
                "nomeAttrazione='" + nomeAttrazione + '\'' +
                ", codiceAttrazione='" + codiceAttrazione + '\'' +
                ", tipoAttrazione=" + tipoAttrazione +
                ", capacitaOraria=" + capacitaOraria +
                ", altezzaMinimaCm=" + altezzaMinimaCm +
                ", aperta=" + aperta +
                ", areaTematica=" + areaTematica +
                '}';
    }
}
