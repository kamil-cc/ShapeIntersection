package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.math.BigDecimal; //Potrzebne do zaokrąglania
import java.awt.geom.GeneralPath;

/**
 * Klasa przechowująca nieokreśloną figurę (współrzędne środka i punkty
 * ograniczające figurę)
 * @author Kamil Burzyński
 */
public class Figura {

    /**
     * Lista punktów ograniczających figurę
     */
    protected List<Point2D> punkty;
    /**
     * Dokładność zaokrąglania współrzędnych
     */
    protected final int miejscaPoPrzecinku;
    /**
     * Współrzędne środka figury
     */
    protected final Point2D srodek;
    /**
     * Iterator przechodzący po punktach w obwodzie figury
     */
    protected GeneralPath ksztalt;

    /**
     * Tworzy nową figurę o nieokreślonym kształcie
     * @param srodek środek figury
     */
    public Figura(Point2D srodek) {
        ksztalt = new GeneralPath();
        miejscaPoPrzecinku = 2;
        punkty = new ArrayList<Point2D>();
        this.srodek = srodek;
    }

    /**
     * Wyświetla informacje o figurze
     */
    public void wyswietl() {
        System.out.println("Nieokreślona figura");
        System.out.println("");
    }

    /**
     * Zaokrągla liczbę double
     * @param d liczba wejściowa
     * @param decimalPlace dokładność zaokrąglania (ilość miejsc po przecinku)
     * @return zaokrąglona liczba double
     */
    protected static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
