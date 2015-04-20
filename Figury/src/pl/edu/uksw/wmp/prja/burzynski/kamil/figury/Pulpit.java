package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.awt.geom.Point2D;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Klasa przechowuje listę figur wprowadzonych przez użytkownika
 * Odpowiada  również za wyświetlanie komunikatów i wywoływanie
 * odpowiednich funkcji programu
 * @author Kamil Burzyński
 */
public class Pulpit {

    /**
     * Lista figur podanych przez użytkownika
     */
    private List<Figura> pulpit;

    /**
     * Tworzy nową listę figur jako tablicę dynamiczną
     */
    public Pulpit() {
        pulpit = new ArrayList<Figura>();
    }

    /**
     * Uruchamia główną pętlę programu
     */
    public void wykonajProgram() {
        int opcja = 0;
        Przeciecie przeciecie = null;
        Scanner buforOdczytu = new Scanner(System.in);
        wyswietlPowitanie();
        while (true) {
            wyswietlMenu();
            opcja = buforOdczytu.nextInt();
            if (opcja == 0) {//Wczytano opcję kończenia programu
                break;
            }
            switch (opcja) {//Wybrano inną opcję
                case 1:
                    dodajFigure(TypFigury.kwadrat);
                    break;
                case 2:
                    dodajFigure(TypFigury.kolo);
                    break;
                case 3:
                    dodajFigure(TypFigury.trojkat);
                    break;
                case 4:
                    dodajFigure(TypFigury.elipsa);
                    break;
                case 5:
                    wyswietlFigury();
                    break;
                case 6:
                    przeciecie = new Przeciecie(pulpit);
                    przeciecie.sprawdzPrzeciecie();
                    break;
                case 7:
                    usunFigure();
                    break;
                default:
                    zlaOpcja();
                    break;

            }
        }
        wyswietlPozegnanie();
    }

    private void dodajFigure(TypFigury typ) {
        Scanner pomBufor = new Scanner(System.in);
        double x;
        double y;
        Point2D srodek;
        Point2D wspolrzedna1;
        Figura nowaFigura;
        switch (typ) {
            case kwadrat:
                System.out.println("Dodawanie kwadratu:");
                System.out.println("\tPodaj środek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                srodek = new Point2D.Double(x, y);
                System.out.println("\tPodaj wierzchołek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                wspolrzedna1 = new Point2D.Double(x, y);
                nowaFigura = new Kwadrat(srodek, wspolrzedna1);
                System.out.println("Dodano kwadrat.");
                System.out.println("");
                break;
            case kolo:
                System.out.println("Dodawanie koła:");
                System.out.println("\tPodaj środek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                srodek = new Point2D.Double(x, y);
                System.out.println("\tPodaj punkt przecięcia ze styczną:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                wspolrzedna1 = new Point2D.Double(x, y);
                nowaFigura = new Kolo(srodek, wspolrzedna1);
                System.out.println("Dodano koło.");
                System.out.println("");
                break;
            case trojkat:
                System.out.println("Dodawanie trójkąta równobocznego:");
                System.out.println("\tPodaj środek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                srodek = new Point2D.Double(x, y);
                System.out.println("\tPodaj wierzchołek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                wspolrzedna1 = new Point2D.Double(x, y);
                nowaFigura = new Trojkat(srodek, wspolrzedna1);
                System.out.println("Dodano trójkąt równoboczny.");
                System.out.println("");
                break;
            case elipsa:
                System.out.println("Dodawanie elipsy:");
                System.out.println("\tPodaj środek figury:");
                System.out.print("\t\tWspółrzędna X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tWspółrzędna Y:");
                y = pomBufor.nextDouble();
                srodek = new Point2D.Double(x, y);
                System.out.println("\tPodaj półosie figury:");
                System.out.print("\t\tDługość półosi X:");
                x = pomBufor.nextDouble();
                System.out.print("\t\tDługość półosi Y:");
                y = pomBufor.nextDouble();
                wspolrzedna1 = new Point2D.Double(x, y);
                nowaFigura = new Elipsa(srodek, wspolrzedna1);
                System.out.println("Dodano elipsę.");
                System.out.println("");
                break;
            default:
                nowaFigura = null;
                break;
        }
        if (nowaFigura != null) {
            pulpit.add(nowaFigura);
        }

    }

    private void usunFigure () {
        int indeks;
        System.out.println("Podaj numer figury, którą chcesz usunąć:");
        Scanner numer = new Scanner(System.in);
        indeks = numer.nextInt() - 1;
        pulpit.remove(indeks);
    }

    private void wyswietlPowitanie() {
        System.out.println("Witaj!");
        System.out.println("Program służy do sprawdzania czy podane "
                + "przez użytkownika figury przecinają się.");
    }

    private void wyswietlMenu() {
        System.out.println("Podaj jaką operację chcesz wykonać:");
        System.out.println("1 Dodaj kwadrat");
        System.out.println("2 Dodaj koło");
        System.out.println("3 Dodaj trójkąt równoboczny");
        System.out.println("4 Dodaj elipsę");
        System.out.println("5 Wyświetl wszystkie figury na pulpit");
        System.out.println("6 Sprawdź przecięcia");
        System.out.println("7 Usuń figurę o podanym numerze");
        System.out.println("0 Zakończ");

    }

    private void wyswietlPozegnanie() {
        System.out.println("Koniec programu.");
        System.out.println("Autor: Kamil Burzyński");
    }

    private void wyswietlFigury() {
        System.out.println("");
        for (Figura figura : pulpit) {
            System.out.print("Figura nr:" + (pulpit.indexOf(figura) + 1) + " ");
            figura.wyswietl();
        }
    }

    private void zlaOpcja() {
        System.out.println("Podałeś złą opcję. Spróbuj ponownie.");
    }
}
