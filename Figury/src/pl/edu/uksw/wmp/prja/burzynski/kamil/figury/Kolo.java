package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.awt.geom.Point2D;

/**
 * Klasa reprezentująca koło
 * @author Kamil Burzyński
 */
public class Kolo extends Figura {

    private final double iloscKatow;
    private final double kat;
    private final Point2D styczna;
    private final double promien;

    /**
     * Tworzy nowe koło
     * @param srodek środek koła
     * @param punktStycznosci punkt wspolny ze styczną ()
     */
    public Kolo(Point2D srodek, Point2D punktStycznosci) {
        super(srodek);
        styczna = punktStycznosci;//Alias
        /**
         * Koło przybliżone wpisanym dwunastokątem foremnym,
         * można użyć większej ilości kątów,
         * koniecznie parzysta i podzielna przez 3
         */
        iloscKatow = 24;
        kat = 2 * Math.PI / iloscKatow;//Miara kąta wewnętrznego
        promien = srodek.distance(styczna); //Promień
        double punktX;
        double punktY;
        double roundedX;
        double roundedY;
        int licznikKatow = 0;
        //Generowanie punktów
        while (licznikKatow < iloscKatow) {
            punktX = srodek.getX() + promien * Math.cos(licznikKatow * kat);
            punktY = srodek.getY() + promien * Math.sin(licznikKatow * kat);
            //Zaokrąglij
            roundedX = round(punktX, miejscaPoPrzecinku);
            roundedY = round(punktY, miejscaPoPrzecinku);
            //Dodaj punkt
            punkty.add(new Point2D.Double(roundedX, roundedY));
            licznikKatow++;
        }
        //Generowanie kształtu
        Point2D PunktZero = punkty.get(0);
        double x=PunktZero.getX();
        double y=PunktZero.getY();
        ksztalt.moveTo(x,y);
        for (int i=0;i<punkty.size();i+=3) {
            if(i%(licznikKatow-1)==0){
                ksztalt.curveTo(punkty.get(i).getX(),punkty.get(i).getY(), 
                        punkty.get(i+1).getX(), punkty.get(i+1).getY(),
                        punkty.get(0).getX(), punkty.get(0).getY());
            }else{
                ksztalt.curveTo(punkty.get(i).getX(),punkty.get(i).getY(), 
                        punkty.get(i+1).getX(), punkty.get(i+1).getY(),
                        punkty.get(i+2).getX(), punkty.get(i+2).getY());
            }
        }
        ksztalt.closePath();
    }

    @Override
     /**
     * Wyświetla informacje o kole (wykorzystuje polimorfizm)
     */
    public void wyswietl() {
        System.out.println("koło o współrzędnych:");
        System.out.println("\tŚrodek:");
        System.out.println("\t\tX=" + srodek.getX());
        System.out.println("\t\tY=" + srodek.getY());
        System.out.println("\tPunkt styczności:");
        System.out.println("\t\tX=" + styczna.getX());
        System.out.println("\t\tY=" + styczna.getY());
        System.out.println("\tPromień");
        System.out.println("\t\tr=" + promien);
        /**
         * Poniższe instrukcje są opcjonalne
         *
        System.out.println("\tAproksymacja punktami:");
        for (Point2D punkt : punkty) {
            System.out.println("\t\tnr "+(punkty.indexOf(punkt) + 1) +
                    ": X="+ punkt.getX() + "; Y=" + punkt.getY());
        }
        */
        System.out.println("");
    }
}
