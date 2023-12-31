import auxiliar.Auxiliar;
import models.Dados;
import models.Formulas;
import models.ValorPeso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Menu {
    File tempDir = new File(System.getProperty("user.dir") + File.separator + "temp");
    File mediaPonderadaTemp = new File("temp/tempMediaPonderada");

    public void menu() {
        System.out.println("BEM-VINDO AO PROGRAMA DE CALCULO DE PROBABILIDADE E INFERÊNCIA ESTATÍSTICA! ");
        System.out.println();
        while (true) {
            boolean fechar = false;
            Dados dados = new Dados();
            Auxiliar aux = new Auxiliar();
            Formulas formulas = new Formulas();

            System.out.println("SELECIONE DENTRE AS SEGUINTES OPÇÕES: ");
            System.out.println("1 - CRIAR UM CONJUNTO DE DADOS");
            System.out.println("2 - IMPRIMIR A TABELA COM AS CLASSES, FREQUÊNCIA, PONTO MÉDIO, FREQUÊNCIA RELATIVA E FREQUÊNCIA ACUMULADA");
            System.out.println("3 - CALCULAR A MÉDIA, MEDIANA E MODA");
            System.out.println("4 - CRIAR CONJUNTO DE DADOS PARA MÉDIA PONDERADA");
            System.out.println("5 - CALCULAR MÉDIA PONDERADA");
            System.out.println("6 - CALCULAR O DESVIO, VARIÂNCIA POPULACIONAL E DESVIO PADRÃO POPULACIONAL");
            System.out.println("7 - CALCULAR O DESVIO, VARIÂNCIA AMOSTRAL E DESVIO PADRÃO AMOSTRAL");
            System.out.println("8 - CALCULAR ");
            System.out.println();
            System.out.println("99 - ENCERRAR O PROGRAMA");
            int opcao = aux.lerInt("Digite a opção desejada: ");
            System.out.println();

            int quantidadeDeDados = 0;
            int quantidadeDeClasses;
            ArrayList<Double> arr;
            ArrayList<ValorPeso> valorEPeso;
            switch (opcao) {
                case 1:
                    quantidadeDeDados = aux.lerInt("Digite a quantidade de dados do conjunto que você possui: ");
                    dados.criaArrDoubleEGrava(tempDir);
                    System.out.println();
                    break;

                case 2:
                    arr = aux.selecionaERecuperaArray(tempDir);
                    quantidadeDeClasses = aux.lerInt("Digite a quantidade de Classes que você deseja: ");
                    ArrayList<Double> limitesInferiores = formulas.limitesInferiores(arr, quantidadeDeClasses);
                    ArrayList<Double> limitesSuperiores = formulas.limitesSuperiores(limitesInferiores, arr, quantidadeDeClasses);
                    ArrayList<Double> frequencias = formulas.calcularFrequencia(arr, limitesInferiores, limitesSuperiores);
                    ArrayList<Double> pontosMedios = formulas.calcularPontoMedio(limitesInferiores, limitesSuperiores);
                    ArrayList<Double> frequenciasRelativas = formulas.calcularFrequenciasRelativas(frequencias, arr.size());
                    ArrayList<Double> frequenciasAcumuladas = formulas.calcularFrequenciasAcumuladas(frequencias);

                    aux.imprimirBonitinho(quantidadeDeClasses, limitesInferiores, limitesSuperiores, frequencias, pontosMedios, frequenciasRelativas, frequenciasAcumuladas);
                    System.out.println();
                    break;

                case 3:
                    arr = aux.selecionaERecuperaArray(tempDir);

                    System.out.println("MÉDIA: " + formulas.calcularMedia(arr));
                    System.out.println("MEDIANA: " + formulas.calcularMediana(arr));
                    System.out.println("MODA: " + formulas.calcularModa(arr));
                    System.out.println();
                    break;

                case 4:
                    quantidadeDeDados = aux.lerInt("Digite a quantidade de dados do conjunto que você possui: ");
                    dados.criaArrDoubleEGravaParaMediaPonderada(quantidadeDeDados, tempDir);
                    System.out.println();
                    break;

                case 5:
                    valorEPeso = aux.selecionaERecuperaArrayValorPeso(mediaPonderadaTemp);
                    double mediaPonderada = formulas.calcularMediaPonderada(valorEPeso);
                    System.out.println("MÉDIA PONDERADA: " + mediaPonderada);
                    System.out.println();
                    break;

                case 6:
                    arr = aux.selecionaERecuperaArray(tempDir);
                    double amplitude = Collections.max(arr) - Collections.min(arr);
                    ArrayList<Double> desvios = formulas.desvios(arr);
                    ArrayList<Double> quadrados = formulas.quadrados(desvios);

                    aux.imprimirValoresDesviosEQuadrados(arr, desvios, quadrados);
                    System.out.println("VARIÂNCIA POPULACIONAL: " + formulas.varianciaPopulacional(quadrados, amplitude));
                    System.out.println("DESVIO PADRÃO POPULACIONAL: " + formulas.desvioPadraoPopulacional(quadrados, amplitude));
                    break;

                case 7:
                    arr = aux.selecionaERecuperaArray(tempDir);
                    desvios = formulas.desvios(arr);
                    quadrados = formulas.quadrados(desvios);

                    aux.imprimirValoresDesviosEQuadrados(arr, desvios, quadrados);
                    System.out.println("VARIÂNCIA POPULACIONAL: " + formulas.varianciaAmostral(quadrados));
                    System.out.println("DESVIO PADRÃO POPULACIONAL: " + formulas.desvioPadraoAmostral(quadrados));
                    break;

                case 8:
                    valorEPeso = aux.selecionaERecuperaArrayValorPeso(mediaPonderadaTemp);
                    ArrayList<Double> valorXFrequencia = formulas.valoresVezesFrequencias(valorEPeso);
                    ArrayList<Double> valorMenosMedia = formulas.valorMenosMedia(valorEPeso);
                    ArrayList<Double> issoAoQuadrado = formulas.quadrados(valorMenosMedia);
                    ArrayList<Double> issoVezesFrequencia = formulas.quadradoVezesFrequencia(valorEPeso);
                    double mediaAmostral = formulas.mediaAmostral(valorEPeso);
                    double desvioPadraoAmostralAgrupado = formulas.desvioPadraoAmostralComPesos(valorEPeso);

                    aux.imprimeCoisasAmostraisComPesos(valorEPeso,valorXFrequencia,valorMenosMedia,issoAoQuadrado,issoVezesFrequencia);
                    System.out.println("MÉDIA AMOSTRAL: " + formulas.mediaAmostral(valorEPeso));
                    System.out.println("DESVIO PADRÃO AMOSTRAL COM PESOS: " + formulas.desvioPadraoAmostralComPesos(valorEPeso));
                case 99:
                    fechar = true;
                    break;
            }

            if (fechar) {
                System.out.println("ENCERRANDO O PROGRAMA...");
                break;
            }
            System.out.println();
        }
    }


}

