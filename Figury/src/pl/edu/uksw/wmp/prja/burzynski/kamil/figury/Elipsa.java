package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.awt.geom.Point2D;

/**
 * Klasa reprezentująca elipsę
 * @author Kamil Burzyński
 */
public class Elipsa extends Figura {

    private final double iloscKatow;
    private final double kat;
    private Point2D pOsie;

    /**
     * Tworzy nową elipsę
     * @param srodek punkt przecięcia półosi elipsy
     * @param polOsie współrzędne osi x oraz osi y
     */
    public Elipsa(Point2D srodek, Point2D polOsie) {
        super(srodek);
        pOsie=polOsie; //Alias
        /**
         * Elipsa przybliżone wpisanym dwunastokątem
         * można użyć większej ilości kątów,
         * koniecznie parzysta i podzielna przez 3
         */
        iloscKatow = 24;
        //Punkty są rozłożone nieregularnie
        kat = 2 * Math.PI / iloscKatow; 

        double punktX;
        double punktY;
        double roundedX;
        double roundedY;
        int licznikKatow = 0;
        //Przesuń (pomniejsz) osie o współrzędne środka
        Point2D przesunietePolOsie =
                new Point2D.Double((polOsie.getX() - srodek.getX()),
                (polOsie.getY() - srodek.getY()));
        while (licznikKatow < iloscKatow) {
            //Obróć
            punktX = przesunietePolOsie.getX() * Math.cos(licznikKatow * kat);
            punktY = przesunietePolOsie.getY() * Math.sin(licznikKatow * kat);
            //przesuń (powiększ) punkty o współrzędne środka
            punktX += srodek.getX();
            punktY += srodek.getY();
            roundedX = round(punktX, miejscaPoPrzecinku);
            roundedY = round(punktY, miejscaPoPrzecinku);
            punkty.add(new Point2D.Double(roundedX, roundedY));
            licznikKatow++;
        }
        Point2D PunktZero = punkty.get(0);
        double x = PunktZero.getX();
        double y = PunktZero.getY();
        ksztalt.moveTo(x, y);
        //Generowanie kształtu
        for (int i = 0; i < punkty.size(); i += 3) {
            if (i % (licznikKatow - 1) == 0) {
                ksztalt.curveTo(punkty.get(i).getX(), punkty.get(i).getY(),
                        punkty.get(i + 1).getX(), punkty.get(i + 1).getY(),
                        punkty.get(0).getX(), punkty.get(0).getY());
            } else {
                ksztalt.curveTo(punkty.get(i).getX(),
                        punkty.get(i).getY(), punkty.get(i + 1).getX(),
                        punkty.get(i + 1).getY(), punkty.get(i + 2).getX(),
                        punkty.get(i + 2).getY());
            }
        }
        ksztalt.closePath();
    }

    @Override
     /**
     * Wyświetla informacje o elipsie (wykorzystuje polimorfizm)
     */
    public void wyswietl() {
        System.out.println("elipsa o współrzędnych:");
        System.out.println("\tŚrodek:");
        System.out.println("\t\tX=" + srodek.getX());
        System.out.println("\t\tY=" + srodek.getY());
        System.out.println("\tPółosie");
        System.out.println("\t\tDługość półosi X=" + pOsie.getX());
        System.out.println("\t\tDługość półosi Y=" + pOsie.getY());
        /**
         * Poniższe instrukcje są opcjonalne
         *
        System.out.println("\tAproksymacja punktami:");
        for (Point2D punkt : punkty) {
            System.out.println("\t\tnr " + (punkty.indexOf(punkt) + 1)
                    + ": X=" + punkt.getX() + "; Y=" + punkt.getY());
        }
        */
        System.out.println("");
    }
}
