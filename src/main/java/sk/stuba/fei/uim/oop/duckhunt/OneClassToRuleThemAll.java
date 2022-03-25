package sk.stuba.fei.uim.oop.duckhunt;

import java.util.Scanner;

public class OneClassToRuleThemAll
{
    HraciePole hraciePole = new HraciePole();

    BalikKariet balik = new BalikKariet();

    Hrac[] hrac = new Hrac[6];

    Scanner input = new Scanner( System.in );

    int pocet_hracov;

    public OneClassToRuleThemAll()
    {
        System.out.print("Zadaj pocet hracov [1-6]: ");

        pocet_hracov = input.nextInt();
        System.out.println(" ");
        System.out.println(" ");

        tvorbaHracov(pocet_hracov);
        hraciePole.vlozitKackyNaPole(pocet_hracov);
    }

    public void hra()
    {
        int hrac_na_tahu = 0;
        do {
            if(hrac[hrac_na_tahu].getZivoty() != 0)
            {
                koloHry(hrac_na_tahu);
            }

            if(++hrac_na_tahu >= pocet_hracov)
            {
                hrac_na_tahu = 0;
            }
        }while (true);
    }

    public void koloHry(int hrac_na_tahu)
    {
        int vybrana_karta;

        System.out.println(" ");
        System.out.println(" ");

        hraciePole.vypisHernehoPola();

        System.out.println(" ");

        System.out.println("Na tahu je Hrac-" + (hrac_na_tahu+1) + "  |  kacky v rybniku: " + hrac[hrac_na_tahu].getZivoty());
        hrac[hrac_na_tahu].vypisRuky();

        System.out.print("Vyber kartu [1-3]: ");
        vybrana_karta = (input.nextInt()-1);

        System.out.println(" ");
        System.out.println("Pouzil si kartu: " + hrac[hrac_na_tahu].vypisatKartu(vybrana_karta));

        Kacka mrtva_kacka = pouzitaAkcnaKarta(hrac_na_tahu, vybrana_karta);

        if(mrtva_kacka != null)
        {
            hrac[vlastnikKacky(mrtva_kacka)].odcitanieZivotov();//odobrat zivot vlastnikovi kacky
        }

        doplnitKartu(hrac_na_tahu);
    }

    public void tvorbaHracov(int pocet_hracov)
    {
        for (int i = 0; i < pocet_hracov; i++)
        {
            hrac[i] = new Hrac();
            System.out.println("Hrac-" + (i+1) + " bol vytvoreny!");

            for(int j = 0; j < 3; j++)
            {
                doplnitKartu(i);
            }
        }
    }

    public int vlastnikKacky(Kacka mrtva_kacka)
    {
        int vlastnik = 0;

        String mrtva_kacka_string = toString(mrtva_kacka);

        if(mrtva_kacka_string.equals("Kacka_Hraca-1"))
            vlastnik = 0;
        else if(mrtva_kacka_string.equals("Kacka_Hraca-2"))
            vlastnik = 1;
        else if(mrtva_kacka_string.equals("Kacka_Hraca-3"))
            vlastnik = 2;
        else if(mrtva_kacka_string.equals("Kacka_Hraca-4"))
            vlastnik = 3;
        else if(mrtva_kacka_string.equals("Kacka_Hraca-5"))
            vlastnik = 4;
        else if(mrtva_kacka_string.equals("Kacka_Hraca-6"))
            vlastnik = 5;

        System.out.println("Mrtva Kacka: " + mrtva_kacka_string);

        return vlastnik;
    }

    public Kacka pouzitaAkcnaKarta(int hrac_na_tahu, int vybrana_karta)
    {
        String pouzita_karta = hrac[hrac_na_tahu].vypisatKartu(vybrana_karta);

        Kacka mrtva_kacka = null;

        if(pouzita_karta.equals("Zamierit"))
        {
            System.out.println("Na aku poziciu chces zamierit [1-6]:");
            hraciePole.zamierit(input.nextInt()-1);
        }
        else if(pouzita_karta.equals("Vystrelit"))
        {
            System.out.println("Na aku poziciu chces vystrelit [1-6]:");
            mrtva_kacka = hraciePole.vystrelit(input.nextInt()-1);
        }
        else if(pouzita_karta.equals("DivokyBill"))
        {
            System.out.println("Na aku poziciu chces pouzit Divokeho Billa [1-6]:");
            mrtva_kacka = hraciePole.divokyBill(input.nextInt()-1);
        }
        else if(pouzita_karta.equals("KacaciPochod"))
            hraciePole.kacaciPochod();
        else if(pouzita_karta.equals("TurboKacka"))
        {
            System.out.println("Ktora kacka je TURBOKACKA [1-6]:");
            hraciePole.turboKacka(input.nextInt()-1);
        }
        else if(pouzita_karta.equals("Rosambo"))
            hraciePole.rosambo();
        else if(pouzita_karta.equals("KacaciTanec"))
            hraciePole.kacaciTanec();

        balik.vratitDoBaliku(hrac[hrac_na_tahu].pouzitKartu(vybrana_karta));

        return mrtva_kacka;
    }

    public void doplnitKartu(int hrac_na_tahu)
    {
        hrac[hrac_na_tahu].pridatKartu(balik.vlozitKartu());
    }

    public String toString(Kacka kacka){
        return String.format(String.valueOf(kacka));
    }
}
