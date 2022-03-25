package sk.stuba.fei.uim.oop.duckhunt;

import java.util.ArrayList;

public class Hrac
{
    private int zivoty = 5;

    private ArrayList<Karta> karty_v_ruke;

    public Hrac()
    {
        karty_v_ruke = new ArrayList<>();
    }

    public void vypisRuky()
    {
        final int kariet_v_ruke = 3;

        for (int i = 0; i < kariet_v_ruke; i++)
        {
            System.out.println((i+1) + ": " + karty_v_ruke.get(i));
        }
    }

    public void odcitanieZivotov()
    {
        this.zivoty--;
    }

    public Karta pouzitKartu(int pozicia)
    {
        return karty_v_ruke.remove(pozicia);
    }

    public String vypisatKartu(int pozicia)
    {
        return toString(pozicia);
    }

    public void pridatKartu(Karta karta_z_baliku)
    {
        karty_v_ruke.add(karta_z_baliku);
    }

    public int getZivoty() {
        return zivoty;
    }

    public String toString(int pozicia)
    {
        return String.format(String.valueOf(karty_v_ruke.get(pozicia)));
    }
}