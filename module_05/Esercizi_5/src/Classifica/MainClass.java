package Classifica;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        Squadra roma = new Squadra(1, "as roma", null);
        Squadra lazio = new Squadra(2, "ss lazio", null);
        Squadra juve = new Squadra(3, "fc rubentus", null);
        Squadra[] teams = new Squadra[] {roma, lazio, juve};
        Classifica serieAChart = new Classifica(teams);
        serieAChart.esitoPartita(roma, 2, lazio, 1);
        serieAChart.esitoPartita(roma, 3, lazio, 1);
        serieAChart.esitoPartita(juve, 2, lazio, 1);
        serieAChart.esitoPartita(juve, 3, lazio, 1);
        Squadra[] chart = serieAChart.getClassifica();
        System.out.println(Arrays.toString(chart));
        Squadra bestAttack = serieAChart.getMigliorAttacco();
        Squadra worstDefese = serieAChart.getPeggiorDifesa();
        System.out.println(bestAttack.toString());
        System.out.println(worstDefese.toString());
    }
}
