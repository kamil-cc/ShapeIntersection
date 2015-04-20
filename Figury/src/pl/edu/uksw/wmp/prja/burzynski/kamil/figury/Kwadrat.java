package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.awt.geom.Point2D;

/**
 * Klasa reprezentująca kwadrat
 * @author Kamil Burzyński
 */
public class Kwadrat extends Figura {

    private final double iloscKatow;
    private final double kat;
    private final Point2D wierzcholek;

    /**
     * Tworzy nowy kwadrat
     * @param srodek środek kwadratu
     * @param punktNarozny wierzchołek kwadratu
     */
    public Kwadrat(Point2D srodek, Point2D punktNarozny) {
        super(srodek);
        wierzcholek = punktNarozny; //Alias
        iloscKatow = 4;
        kat = 2 * Math.PI / iloscKatow;
        double punktX;
        double punktY;
        double roundedX;
        double roundedY;
        int licznikKatow = 1;
        //Generowanie wierzchołków
        //Przesuń (pomniejsz) o współrzędne środka
        Point2D przesunietyWierzcholek =
                new Point2D.Double((wierzcholek.getX() - srodek.getX()),
                (wierzcholek.getY() - srodek.getY()));
        //Dodaje pierwszy wierzchołek podany przez użytkownika
        punkty.add((Point2D) wierzcholek.clone());
        while (licznikKatow < iloscKatow) {
            //Obróć
            punktX = przesunietyWierzcholek.getX()
                    * Math.cos(licznikKatow * kat)
                    - przesunietyWierzcholek.getY()
                    * Math.sin(licznikKatow * kat);
            punktY = przesunietyWierzcholek.getX()
                    * Math.sin(licznikKatow * kat)
                    + przesunietyWierzcholek.getY()
                    * Math.cos(licznikKatow * kat);
            //przesuń (powiększ) o współrzędne środka
            punktX += srodek.getX();
            punktY += srodek.getY();
            //Zaokrąglij
            roundedX = round(punktX, miejscaPoPrzecinku);
            roundedY = round(punktY, miejscaPoPrzecinku);
            //Dodaj nowy punkt
            punkty.add(new Point2D.Double(roundedX, roundedY));
            licznikKatow++;
        }
        //Generowanie kształtu
        Point2D ostatniPunkt = punkty.get((punkty.size() - 1));
        double x = ostatniPunkt.getX();
        double y = ostatniPunkt.getY();
        ksztalt.moveTo(x, y);
        for (Point2D bierzacyPunkt : punkty) {
            ksztalt.lineTo(bierzacyPunkt.getX(), bierzacyPunkt.getY());
        }
        ksztalt.closePath();
    }

    @Override
    /**
     * Wyświetla informacje o kwadracie (wykorzystuje polimorfizm)
     */
    public void wyswietl() {
        System.out.println("kwadrat o współrzędnych:");
        System.out.println("\tŚrodek:");
        System.out.println("\t\tX=" + srodek.getX());
        System.out.println("\t\tY=" + srodek.getY());
        System.out.println("\tWierzchołki:");
        for (Point2D punkt : punkty) {
            System.out.println("\t\tnr " + (punkty.indexOf(punkt) + 1)
                    + ": X=" + punkt.getX() + "; Y=" + punkt.getY());
        }
        System.out.println("");
    }
}
