package it.its.modulo.java;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;
import it.its.modulo.java.gestioni.GestioneBiglietti;
import it.its.modulo.java.gestioni.GestioneParcoDivertimenti;
import it.its.modulo.java.gestioni.GestioneVisitatori;
import it.its.modulo.java.model.Attrazione;
import it.its.modulo.java.model.Biglietto;
import it.its.modulo.java.model.Visitatore;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MainParcoDivertimenti {
    public static void main(String[] args) {

        Map<String, String> percorsi = new HashMap<>();
        try (BufferedReader read = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader()
                        .getResourceAsStream("percorsi.txt"))))){
            String linea;
            while ((linea = read.readLine()) != null) {
                String[] strings = linea.split("=");
                percorsi.put(strings[0].trim(), strings[1].trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        GestioneBiglietti gestioneBiglietti = new GestioneBiglietti();
        GestioneParcoDivertimenti gestioneParcoDivertimenti = new GestioneParcoDivertimenti("FASANOLANDIA");
        GestioneVisitatori gestioneVisitatori = new GestioneVisitatori();
        for (Map.Entry<String, String> entry : percorsi.entrySet()) {
            switch (entry.getKey()) {
                case "PercorsoAttrazioni":
                    List<Attrazione> listaAttrazione = MainParcoDivertimenti.caricaCollezione(entry.getValue(), Attrazione.class);
                    for (Attrazione attrazione : listaAttrazione) {
                        gestioneParcoDivertimenti.addAttrazione(attrazione);
                    }
                    break;
                case "PercorsoVisitatori":
                    List<Visitatore> listaVisitatori = MainParcoDivertimenti.caricaCollezione(entry.getValue(), Visitatore.class);
                    for (Visitatore visitatori : listaVisitatori) {
                        gestioneVisitatori.aggiungiVisitatore(visitatori);
                    }
                    break;
                case "PercorsoBiglietti":
                    List<Biglietto> listaBiglietto = MainParcoDivertimenti.caricaCollezione(entry.getValue(), Biglietto.class);
                    for (Biglietto biglietto : listaBiglietto) {
                        gestioneBiglietti.aggiungiBiglietto(biglietto);
                    }
                    break;
            }
        }
    }

    private static <T> List<T> caricaCollezione  (String percorso, Class<T> clazz) {
        List<T> risultato = new ArrayList<>();
        try (BufferedReader readed = new BufferedReader(new FileReader(percorso))) {
            Gson gson = new Gson();
            String line;
            while ((line = readed.readLine()) != null) {
                risultato.add(gson.fromJson(line, clazz));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return risultato;
    }

    private static void verificaEsistenzaFile(URL urlToCheck, String fileName){

        if (urlToCheck == null) {
            try {
                URI resourceUri = Objects.requireNonNull(Main.class.getClassLoader()
                                .getResource(""))
                        .toURI();
                Path path = Path.of(resourceUri);
                Files.createFile(Path.of(path.toString(), fileName));
            } catch (Exception e) {
                System.out.println("Errore nella gestione dei file");
                e.printStackTrace();
            }
        }
    }
}