package sk.stuba.fei.uim.oop.duckhunt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BalikKaciek {
    private ArrayList<Kacka> balik_kaciek;

    public BalikKaciek() {
        balik_kaciek = new ArrayList<>();
    }

    public void vytovritKacky(int pocet_hracov)
    {
        List<String> kacky = Kacka.getNazvyKaciek();

        for(int i = 0; i <= pocet_hracov; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                balik_kaciek.add(new Kacka((kacky.get(i))));
            }
        }
    }

    public void vratitDoBaliku(Kacka kacka_z_pola)
    {
        balik_kaciek.add(kacka_z_pola);
    }

    public Kacka vlozitKacku()
    {
        if (balik_kaciek.size()>0)
            return balik_kaciek.remove(0);
        else
            return null;
    }

    public void zamiesatKacky()
    {
        Collections.shuffle(balik_kaciek);
    }
}
