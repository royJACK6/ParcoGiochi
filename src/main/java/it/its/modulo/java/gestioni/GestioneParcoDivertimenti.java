package it.its.modulo.java.gestioni;

import it.its.modulo.java.model.AreaTematica;
import it.its.modulo.java.model.Attrazione;
import it.its.modulo.java.model.Giostra;
import it.its.modulo.java.model.Visitatore;

import java.util.*;

public class GestioneParcoDivertimenti {
    private final String nomeParco;
    private final Set<AreaTematica> listAreeTematiche;
    private final Map<String, Attrazione> mappaAttrazioni;
    private final Map<Attrazione, Queue<Visitatore>> codaAttrazione;

    public GestioneParcoDivertimenti(String nomeParco) {
        this.nomeParco = nomeParco;
        this.listAreeTematiche = new HashSet<>();
        this.mappaAttrazioni = new HashMap<>();
        this.codaAttrazione = new HashMap<>();
    }

    public String getNomeParco() {
        return nomeParco;
    }

    public Set<AreaTematica> getListAreeTematiche() {
        return listAreeTematiche;
    }

    public Map<String, Attrazione> getMappaAttrazioni() {
        return mappaAttrazioni;
    }
    public void addAreaTematica(AreaTematica areaTematica){
        this.listAreeTematiche.add(areaTematica);
    }

    public void rimuoviAreaTematica(String nomeArea) {
        this.listAreeTematiche
                .removeIf(areaTematica -> nomeArea.equals(areaTematica.getNomeArea()));
    }

    public void aggiungiAttrazioneAreaTematica (String nomeArea, String codiceAttrazione){
        AreaTematica areaTematica = this.listAreeTematiche
                .stream()
                .filter(area -> nomeArea.equals(area.getNomeArea()))
                .findFirst()
                .orElse(null);
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        if (areaTematica != null && attrazione != null)
            areaTematica.aggiungiAttrazione(attrazione);

    }
    public void rimuoviAttrazioneAreaTematica (String nomeArea, String codiceAttrazione){
        AreaTematica areaTematica = this.listAreeTematiche
                .stream()
                .filter(area -> nomeArea.equals(area.getNomeArea()))
                .findFirst()
                .orElse(null);
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        if (areaTematica != null && attrazione != null)
            areaTematica.rimuoviAttrazione(attrazione);

    }
    public void addAttrazione(Attrazione attrazione){
        this.mappaAttrazioni.put(attrazione.getCodiceAttrazione(), attrazione);
        this.codaAttrazione.put(attrazione, new ArrayDeque<>());
    }

    public void removeAttrazione(String codiceAttrazione){
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        if (attrazione != null) {
            this.mappaAttrazioni.remove(codiceAttrazione);
            this.codaAttrazione.remove(attrazione);
        }


    }

    public boolean apriChiudiAttrazione (String codiceAttrazione){
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        if (attrazione != null){
            attrazione.setAperta(!attrazione.isAperta());
            return attrazione.isAperta();
        }
        throw new IllegalArgumentException("Attrazione non trovata");
    }

    public void visualizzaDettagliAttrazione (String codiceAttrazione){
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        if (attrazione != null){
            System.out.println("Descrizione Attrazione :" + attrazione.getDescrzioneCompleta());
            System.out.println(attrazione);
        }
    }

    public List<Attrazione> ricercaAttrazione (Class<? extends Attrazione> clazz){
        return this.mappaAttrazioni
                .values()
                .stream()
                .filter(attrazione -> attrazione.getClass().equals(clazz))
                .toList();
    }

    public List<Attrazione> ricercaAttrazione (String areaTematica){
        AreaTematica area = this.listAreeTematiche
                .stream()
                .filter(areaVal -> areaVal.getNomeArea().equals(areaTematica))
                .findFirst()
                .orElse(null);
        if (area != null)
            return area.getAttrazioneList();
        throw new IllegalArgumentException("Richiesta Area Tematica non trovata");
    }

    public List<Attrazione> ricercaAttrazione (boolean isAperta){
        return this.mappaAttrazioni
                .values()
                .stream()
                .filter(attrazione -> attrazione.isAperta() == isAperta)
                .toList();
    }

    public int aggiungiVisitatoreCoda(String codiceAttrazione, Visitatore visitatore){

        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        boolean puoAccedere = true;
        if (attrazione instanceof Giostra giostra) {
            puoAccedere = (giostra.getLimiteEta() ==null || giostra.getLimiteEta() <= visitatore.getEta())
                    && giostra.getAltezzaMinimaCm() <= visitatore.getAltezzaCm();
        }
        if (puoAccedere) {
            Queue<Visitatore> codaAttrazione = this.codaAttrazione.get(attrazione);
            codaAttrazione.add(visitatore);
            return codaAttrazione.size();
        }
        return 0;
    }

    public void accediAttrazione (String codiceAttrazione, Visitatore visitatore, int posizioneInCoda){
        Attrazione attrazione = this.mappaAttrazioni.get(codiceAttrazione);
        Queue<Visitatore> codaAttrazione = this.codaAttrazione.get(attrazione);
        System.out.println("Tu sei il "
                    + posizioneInCoda
                    + "° visitatore in coda.");
        if (codaAttrazione.contains(visitatore)) {
            while (!visitatore.equals(codaAttrazione.poll())) {
                System.out.println("Ingresso visitatore");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Sei il prossimo ");
        }else {
            System.out.println("Non sei in coda per questa attrazione");
        }
    }

    public int tempoAttesaInCoda(String codiceAttrazione) {
        Attrazione attrazione= this.mappaAttrazioni.get(codiceAttrazione);
        Queue<Visitatore> codaAttrazione = this.codaAttrazione.get(attrazione);
        return attrazione.calcolaTempoAttesaMedio(codaAttrazione.size());
    }

    public List<Attrazione> attrazionesVisitabili(Visitatore visitatore) {
        return this.mappaAttrazioni
                .values()
                .stream()
                .filter(attrazione -> {
                     if(attrazione instanceof Giostra giostra)
                         return giostra.isAperta()
                        && (giostra.getLimiteEta() ==null
                                 || giostra.getLimiteEta() <= visitatore.getEta())
                            && giostra.getAltezzaMinimaCm() <= visitatore.getAltezzaCm();

                return attrazione.isAperta();
                })
                .toList();
    }
}