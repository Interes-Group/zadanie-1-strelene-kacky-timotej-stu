package sk.stuba.fei.uim.oop.duckhunt;

import java.util.ArrayList;
import java.util.Collections;

public class HraciePole extends BalikKaciek
{
    private ArrayList<Kacka> kacky_na_poli;

    private ArrayList<Kacka> turbokacka;

    private int[] zamierene = {0,0,0,0,0,0};

    private final int pocetPolicok = 6;

    public HraciePole()
    {
        kacky_na_poli = new ArrayList<>();

        turbokacka = new ArrayList<>();
    }

    public void vypisHernehoPola()
    {
        for (int i = 0; i < kacky_na_poli.size(); i++)
        {
            if (zamierene[i] == 1)
            {
                System.out.print((i + 1) + ": Zamierene - ");
            }
            else
            {
                System.out.print((i + 1) + ": Nezamierene - ");
            }

            System.out.println(kacky_na_poli.get(i));
        }
    }

    public void vlozitKackyNaPole(int pocet_hracov)
    {
        vytovritKacky(pocet_hracov);

        zamiesatKacky();

        for (int i = 0; i < pocetPolicok; i++)
        {
            kacky_na_poli.add(vlozitKacku());
        }
    }

    public void zamierit(int pozicia)
    {
        if(zamierene[pozicia] != 1)
        {
            zamierene[pozicia] = 1;
            System.out.println("Zamieril si na poziciu_" + (pozicia+1));
        }
        else
            System.out.println("Na pozíciu už je zamierené.");
    }

    public Kacka vystrelit(int pozicia)
    {
        if(zamierene[pozicia] == 1)
        {
            if("Voda".equals(toString(pozicia)))
            {
                System.out.println("Strelil si do vody!");
                zamierene[pozicia] = 0;
                 return kacky_na_poli.get(pozicia);
            }
            else
            {
                System.out.println("Strelil si do: " + kacky_na_poli.get(pozicia));
                zamierene[pozicia] = 0;
                kacky_na_poli.add(vlozitKacku());
                return kacky_na_poli.remove(pozicia);
            }
        }
        else
        {
            System.out.println("Na poziciu nie je zamierene!");
            return null;
        }
    }

    public Kacka divokyBill(int pozicia)
    {
        if(zamierene[pozicia] == 0)
        {
            zamierit(pozicia);
        }
        return vystrelit(pozicia);
    }

    public void kacaciPochod()
    {
        kacky_na_poli.add(vlozitKacku());
        vratitDoBaliku(kacky_na_poli.remove(0));
    }

    public void turboKacka(int pozicia)
    {
        turbokacka.add(kacky_na_poli.remove(pozicia));

        for (int i = 0; i < (pocetPolicok-1); i++)
        {
            turbokacka.add(kacky_na_poli.remove(0));
        }

        for (int i = 0; i < pocetPolicok; i++)
        {
            kacky_na_poli.add(turbokacka.remove(0));
        }
    }

    public void rosambo()
    {
        Collections.shuffle(kacky_na_poli);
    }

    public void kacaciTanec()
    {
        for (int i = 0; i < pocetPolicok; i++)
        {
            vratitDoBaliku(kacky_na_poli.remove(0));
        }

        zamiesatKacky();

        for (int i = 0; i < pocetPolicok; i++)
        {
            kacky_na_poli.add(vlozitKacku());
        }
    }

    public String toString(int pozicia)
    {
        return String.format(String.valueOf(kacky_na_poli.get(pozicia)));
    }
}
