package it.its.modulo.java.prova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainProva {
    public static void main(String[] args){
        PippoExample pippo = new PippoExample();
        pippo.nome = "Oronzo";
        pippo.cognome = "Scarafile";
        pippo.codiceFiscale = "SCRRNZ91L08D508I";

        try (BufferedWriter write = new BufferedWriter(new FileWriter("pippo_uot.txt", true))) {
            Gson gson = new Gson();
            String json = gson.toJson(pippo);
            System.out.println("JSON Creato: \n" + json );
            write.write(json);
            write.newLine();
        } catch (IOException e) {
            System.out.println("Errore nella scrittura");
            e.printStackTrace();
        }
        MainProva.mainLettura(args);
    }

    public static void mainLettura(String[] args){
        try (BufferedReader read = new BufferedReader( new FileReader("pippo_uot.txt"))) {
            Gson gson = new GsonBuilder().create();
            String json = read.readLine();
            PippoExample pippo = gson.fromJson(json, PippoExample.class);
            System.out.println("JSON letto: \n" + json );
            System.out.println("Oggetto\n" + pippo);
        } catch (IOException e) {
            System.out.println("Errore nella lettura");
            e.printStackTrace();
        }
    }
}