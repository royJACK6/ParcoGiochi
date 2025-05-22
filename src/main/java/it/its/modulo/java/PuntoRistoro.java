package it.its.modulo.java;

import java.util.HashMap;
import java.util.Map;

public class PuntoRistoro extends Attrazione {

    public enum TipoCucina {
        ITALIANA,
        GIAPPONESE,
        COREANA,
        MESSICANA,
        JUNK_FOOD;

        private static final Map<String, TipoCucina> TIPO_CUCINA = new HashMap<>();

        static {
            TIPO_CUCINA.put("italiana", ITALIANA);
            TIPO_CUCINA.put("giapponese", GIAPPONESE);
            TIPO_CUCINA.put("coreana", COREANA);
            TIPO_CUCINA.put("messicana", MESSICANA);
            TIPO_CUCINA.put("junk_food", JUNK_FOOD);
        }

        public static TipoCucina lookUp(String value) {
            if (value == null) throw new IllegalArgumentException("Tipo cucina non inserito");
            value = value.toLowerCase();
            TipoCucina tipoCucina = TIPO_CUCINA.get(value);
            if (tipoCucina == null) throw new IllegalArgumentException("Tipo cucina non trovato" + value);
            return tipoCucina;
        }
    }

    private final Map<String, Double> nemu;
    private final TipoCucina tipoCucina;

    public PuntoRistoro(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta, AreaTematica areaTematica, Map<String, Double> nemu, TipoCucina tipoCucina) {
        super(nomeAttrazione, codiceAttrazione, tipoAttrazione, capacitaOraria, altezzaMinimaCm, aperta, areaTematica);
        this.nemu = nemu;
        this.tipoCucina = tipoCucina;
    }

    public Map<String, Double> getNemu() {
        return nemu;
    }

    public TipoCucina getTipoCucina() {
        return tipoCucina;
    }

    @Override
    protected String getDescrzioneCompleta() {
        return this.getNomeAttrazione() + " (" + this.getAreaTematica().getDescrizione() + ")";
    }

    @Override
    protected int calcolaTempoAttesaMedioU(int personeInCoda) {
        return personeInCoda / getCapacitaOraria();
    }

    @Override
    public String toString() {
        return "PuntoRistoro{" +
                "nomeRistorante='" + this.getNomeAttrazione() + '\'' +
                ", codiceAttrazione='" + this.getCodiceAttrazione() + '\'' +
                ", capacitaOraria=" + this.getCapacitaOraria() +
                ", aperta='" + this.isAperta() +
                ", areaTematica='" + this.getAreaTematica() +
                ", nemu='" + nemu +
                ", tipoCucina='" + tipoCucina +
                '}';
    }
}