package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.io.IOException;

/**
 * Główna klasa programu
 * @author Kamil Burzyński
 */
public class Main {

    /**
     * Tworzy obiekt klasy program i uruchamia główną pętlę programu
     * @param args the command line arguments nie korzysta z argumentów
     * podanych w liniii poleceń
     */
    public static void main(String[] args) throws IOException {
        Pulpit pulpit;
        pulpit = new Pulpit();
        pulpit.wykonajProgram();
    }
}
