package sk.stuba.fei.uim.oop.duckhunt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BalikKariet
{
    private ArrayList<Karta> balik;

    public BalikKariet()
    {
        List<String> karty = Karta.getNazvyKariet();
        List<Integer> pocet = Karta.getPocetKariet();

        balik = new ArrayList<>();

        for(int i = 0; i < pocet.size(); i++)
        {
            for(int j = 0; j < pocet.get(i); j++)
            {
                balik.add(new Karta(karty.get(i)));
            }
        }

        zamiesatKarty();
    }

    public Karta vlozitKartu()
    {
        if (balik.size()>0)
            return balik.remove(0);
        else
            return null;
    }

    public void vratitDoBaliku(Karta karta_z_ruky)
    {
        balik.add(karta_z_ruky);
    }

    public void zamiesatKarty()
    {
        Collections.shuffle(balik);
    }
}
