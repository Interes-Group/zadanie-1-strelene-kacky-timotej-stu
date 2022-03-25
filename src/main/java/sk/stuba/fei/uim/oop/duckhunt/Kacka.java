package sk.stuba.fei.uim.oop.duckhunt;

import java.util.Arrays;
import java.util.List;

public class Kacka
{
    private String nazov_kacky;

    public Kacka(String nazov_kacky)
    {
        setNazovKacky(nazov_kacky);
    }

    public static List<String> getNazvyKaciek()
    {
        return Arrays.asList("Voda", "Kacka_Hraca-1", "Kacka_Hraca-2", "Kacka_Hraca-3", "Kacka_Hraca-4", "Kacka_Hraca-5", "Kacka_Hraca-6");
    }

    public void setNazovKacky(String nazov_kacky)
    {
        List<String> nazvyKaciek = getNazvyKaciek();
        if(nazvyKaciek.contains(nazov_kacky))
        {
            this.nazov_kacky = nazov_kacky;
        }
        else
            throw new IllegalArgumentException("Kacka neexistuje!");
    }

    public String toString()
    {
        return String.format(nazov_kacky);
    }
}
