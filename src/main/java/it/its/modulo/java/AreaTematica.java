package it.its.modulo.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AreaTematica {
    private final String nomeArea;
    private final String descrizione;
    private final List<Attrazione> attrazioneList;

    public AreaTematica(String nomeArea, String descrizione, List<Attrazione> attrazioneList) {
        this.nomeArea = nomeArea;
        this.descrizione = descrizione;
        this.attrazioneList = new ArrayList<>();
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<Attrazione> getAttrazioneList() {
        return attrazioneList;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AreaTematica that)) return false;

        return Objects.equals(nomeArea, that.nomeArea) && Objects.equals(descrizione, that.descrizione);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nomeArea);
        result = 31 * result + Objects.hashCode(descrizione);
        return result;
    }

    @Override
    public String toString() {
        return "AreaTematica{" +
                "nomeArea='" + nomeArea + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }


    public void aggiungiAttrazione(Attrazione attrazione){
        attrazioneList.add(attrazione);
    }
}
