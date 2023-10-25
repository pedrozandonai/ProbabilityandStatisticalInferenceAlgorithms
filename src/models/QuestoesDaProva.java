package models;

import auxiliar.Auxiliar;
import models.Dados;
import models.ValorPeso;

import java.io.File;
import java.util.ArrayList;

public class QuestoesDaProva {
    static File mediaPonderadaTemp = new File("temp/tempMediaPonderada");

    public static void main(String[] args) {
        questaoUm();
    }
    public static void questaoUm(){
        Auxiliar aux = new Auxiliar();
        ArrayList<ValorPeso> dadosAComputar = aux.selecionaERecuperaArrayValorPeso(mediaPonderadaTemp);

        double mediaPonderada = Formulas.calcularMediaPonderada(dadosAComputar);
        double desvioPadrao = Formulas.desvioPadraoAmostralComPesos(dadosAComputar);

        System.out.println("MEDIA: " + mediaPonderada);
        System.out.println("Desvio Padrão: " + desvioPadrao);
    }
}
