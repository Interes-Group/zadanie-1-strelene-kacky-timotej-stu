package sk.stuba.fei.uim.oop.duckhunt;

import java.util.Arrays;
import java.util.List;

public class Karta
{
    private String nazovKarty;

    public Karta(String nazovKarty)
    {
        setNazovKarty(nazovKarty);
    }

    public static List<String> getNazvyKariet()
    {
        return Arrays.asList("Zamierit", "Vystrelit", "DivokyBill", "KacaciPochod", "TurboKacka", "Rosambo", "KacaciTanec");
    }

    public static List<Integer> getPocetKariet()
    {
        return Arrays.asList(12, 10, 2, 6, 1, 2, 1);
    }

    public void setNazovKarty(String nazovKarty)
    {
        List<String> nazvyKariet = getNazvyKariet();
        if(nazvyKariet.contains(nazovKarty))
        {
            this.nazovKarty = nazovKarty;
        }
        else
            throw new IllegalArgumentException("Karta neexistuje!");
    }

    public String toString()
    {
        return String.format(nazovKarty);
    }
}