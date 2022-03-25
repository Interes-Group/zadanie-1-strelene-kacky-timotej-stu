package sk.stuba.fei.uim.oop.duckhunt;

import java.util.Scanner;

public class OneClassToRuleThemAll
{
    HraciePole hraciePole = new HraciePole();

    BalikKariet balik = new BalikKariet();

    Hrac[] hrac = new Hrac[6];

    Scanner input = new Scanner( System.in );

    private int pocet_hracov, pocet_hracov_v_hre;

    public OneClassToRuleThemAll()
    {
        System.out.print("Zadaj pocet hracov [1-6]: ");
        pocet_hracov = (vybratCislo(6)+1);
        pocet_hracov_v_hre = pocet_hracov;

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
        }while (pocet_hracov_v_hre > 1);

        for(int i = 0; i < pocet_hracov; i++)
        {
            if (hrac[i].getZivoty() > 0)
                System.out.println("Hrac-" + (i+1) + "je vitaz!");
        }
    }

    public void koloHry(int hrac_na_tahu)
    {
        int vybrana_karta;

        int vlastnik_kacky;

        System.out.println(" ");
        System.out.println(" ");

        hraciePole.vypisHernehoPola();

        System.out.println(" ");

        System.out.println("Na tahu je Hrac-" + (hrac_na_tahu+1) + "  |  kacky v rybniku: " + hrac[hrac_na_tahu].getZivoty());
        hrac[hrac_na_tahu].vypisRuky();

        System.out.print("Vyber kartu [1-3]: ");
        vybrana_karta = vybratCislo(3);

        System.out.println(" ");
        System.out.println("Pouzil si kartu: " + hrac[hrac_na_tahu].vypisatKartu(vybrana_karta));

        Kacka mrtva_kacka = pouzitaAkcnaKarta(hrac_na_tahu, vybrana_karta);

        if(mrtva_kacka != null)
        {
            vlastnik_kacky = vlastnikKacky(mrtva_kacka);
            hrac[vlastnik_kacky].odcitanieZivotov();//odobrat zivot vlastnikovi kacky

            if (hrac[vlastnik_kacky].getZivoty() == 0)
            {
                pocet_hracov_v_hre--;
            }
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
        int vlastnik;

        switch (toString(mrtva_kacka))
        {
            case "Kacka_Hraca-2":   vlastnik = 1;
                                    break;
            case "Kacka_Hraca-3":   vlastnik = 2;
                                    break;
            case "Kacka_Hraca-4":   vlastnik = 3;
                                    break;
            case "Kacka_Hraca-5":   vlastnik = 4;
                                    break;
            case "Kacka_Hraca-6":   vlastnik = 5;
                                    break;
            default:                vlastnik = 0;
        }

        System.out.println("Mrtva Kacka: " + toString(mrtva_kacka));

        return vlastnik;
    }

    public Kacka pouzitaAkcnaKarta(int hrac_na_tahu, int vybrana_karta)
    {
        String pouzita_karta = hrac[hrac_na_tahu].vypisatKartu(vybrana_karta);

        Kacka mrtva_kacka = null;

        switch (pouzita_karta)
        {
            case "Zamierit":        System.out.println("Na aku poziciu chces zamierit [1-6]:");
                                    hraciePole.zamierit(vybratCislo(6));
                                    break;
            case "Vystrelit":       System.out.println("Na aku poziciu chces vystrelit [1-6]:");
                                    hraciePole.vystrelit(vybratCislo(6));
                                    break;
            case "DivokyBill":      System.out.println("Na aku poziciu chces pouzit Divokeho Billa [1-6]:");
                                    mrtva_kacka = hraciePole.divokyBill(vybratCislo(6));
                                    break;
            case "KacaciPochod":    hraciePole.kacaciPochod();
                                    break;
            case "TurboKacka":      System.out.println("Ktora kacka je TURBOKACKA [1-6]:");
                                    hraciePole.turboKacka(vybratCislo(6));
                                    break;
            case "Rosambo":         hraciePole.rosambo();
                                    break;
            case "KacaciTanec":     hraciePole.kacaciTanec();
                                    break;
            default:                mrtva_kacka = null;
        }

        balik.vratitDoBaliku(hrac[hrac_na_tahu].pouzitKartu(vybrana_karta));

        return mrtva_kacka;
    }

    public int vybratCislo(int max)
    {
        int vybrana_pozicia = (input.nextInt()-1);

        while (vybrana_pozicia <  0 || vybrana_pozicia > (max-1))
        {
            System.out.println("Neplatne cislo! Vyber cislo [1-" + max +"]:");
            vybrana_pozicia = (input.nextInt()-1);
        }

        return vybrana_pozicia;
    }

    public void doplnitKartu(int hrac_na_tahu)
    {
        hrac[hrac_na_tahu].pridatKartu(balik.vlozitKartu());
    }

    public String toString(Kacka kacka){
        return String.format(String.valueOf(kacka));
    }
}