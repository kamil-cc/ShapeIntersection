package pl.edu.uksw.wmp.prja.burzynski.kamil.figury;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za szukanie przecięć figur
 * @author Kamil Burzyński
 */
public class Przeciecie {

    /**
     * Referencja do pulpitu
     */
    private List<Figura> pulpit;

    /**
     * Klasa wewnętrzna
     * Przechowuje pary referencji na figury, które się przecinają
     */
    private class figuryPrzeciecie {

        private Figura pierwsza;
        private Figura druga;

        public figuryPrzeciecie(Figura figura1, Figura figura2) {
            pierwsza = figura1;
            druga = figura2;
        }
    }
    /**
     * Lista przecięć
     */
    private List<figuryPrzeciecie> ktorePrzecinaja;

    /**
     * Tworzy obiekt odpowiedzialny za wyszukiwanie przecięć
     * @param pulpit referencja na listę przechowywanych figur
     */
    public Przeciecie(List<Figura> pulpit) {
        this.pulpit = pulpit;
        ktorePrzecinaja = new ArrayList<figuryPrzeciecie>();
    }

    /**
     * Sprawdza czy występuje przecięcie
     */
    public void sprawdzPrzeciecie() {
        Figura figura2;
        //For przecodzi przez wszystkie figury z pulpitu
        for (Figura figura1 : pulpit) {
            //For przechodzi przez wszystkie figury z pulpitu
            for (int i = pulpit.indexOf(figura1) + 1; i < pulpit.size(); i++) {
                figura2 = pulpit.get(i);
                //Przechodzi po punktach figury 1
                for (Point2D punktFigury1 : figura1.punkty) {
                    //Sprawdza czy figura 2 zawiera w sobie jakiś punkt figury 1
                    if (figura2.ksztalt.contains(punktFigury1)) {
                        dodajPrzeciecie(figura1, figura2);
                    }
                    //Porównuje czy punkty figury 1 i 2 pokrywają się
                    for (Point2D punktFigury2 : figura2.punkty) {
                        if (punktFigury2.equals(punktFigury1)) {
                            dodajPrzeciecie(figura1, figura2);
                        }
                    }
                }
                //Przechodzi po punktach figuiry 2
                for (Point2D punktFig2 : figura2.punkty) {
                    //Sprawdza czy figura 1 zawiera w sobie jakiś punkt figury 2
                    if (figura1.ksztalt.contains(punktFig2)) {
                        dodajPrzeciecie(figura1, figura2);
                    }
                }
            }
        }
        wypiszPrzeciecie();
    }

    private void dodajPrzeciecie(Figura figura1, Figura figura2) {

        for (figuryPrzeciecie nowa : ktorePrzecinaja) {
            //Nie dodawaj przecięcia, ponieważ już jest na liście
            if ((nowa.pierwsza == figura1) && (nowa.druga == figura2)) {
                return;
            }
            if ((nowa.pierwsza == figura2) && (nowa.druga == figura1)) {
                return;
            }
        }
        //Przecięcia nie ma na liście, dodaj nowe
        ktorePrzecinaja.add(new figuryPrzeciecie(figura1, figura2));
    }

    private void wypiszPrzeciecie() {
        System.out.println("");
        if (ktorePrzecinaja.isEmpty()) {
            System.out.println("Brak przecięcia");
        } else {
            for (figuryPrzeciecie nowa : ktorePrzecinaja) {
                System.out.println("Figury: " + 
                        (pulpit.indexOf(nowa.pierwsza) + 1) + " i " +
                        (pulpit.indexOf(nowa.druga) + 1) + " przecinają się");
            }
        }
        System.out.println("");
    }
}
      

  
 
