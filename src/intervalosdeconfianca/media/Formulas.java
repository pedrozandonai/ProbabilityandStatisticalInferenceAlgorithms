package intervalosdeconfianca.media;

import auxiliar.Auxiliar;
import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;
public class Formulas {

    public double intervalosDeConfianca(int opcao){
        /*
        O nível de confiança c é a probabilidade de que a estimativa intervalar contenha o parâmetro populacional,
        supondo que o processo de estimação é repetido um grande número de vezes.
         */

        double noventaPorcento = 1.645;
        double noventaECincoPorcento = 1.96;
        double noventaENovePorcento = 2.575;

        Auxiliar aux = new Auxiliar();

        if ((opcao == 1 || opcao == 2 || opcao == 3)) {
            switch (opcao) {
                case 1 -> {
                    return noventaPorcento;
                }
                case 2 -> {
                    return noventaECincoPorcento;
                }
                case 3 -> {
                    return noventaENovePorcento;
                }
            }
        }else if (opcao == 0){
            System.out.println("+------------INTERVALOS DE CONFIANÇA------------+");
            System.out.println("+-NÍVEL DE CONFIANÇA || ESCORE-Z CORRESPONDENTE-+");
            System.out.println("+-----------------------------------------------+");
            System.out.println("+--1)--90%-----------||-----------1,645---------+");
            System.out.println("+--2)--95%-----------||-----------1,96----------+");
            System.out.println("+--3)--99%-----------||-----------2,575---------+");
            System.out.println("+-----------------------------------------------+");
            opcao = aux.lerInt("Digite o número do intervalo desejado correspondente: ");
            switch (opcao) {
                case 1 -> {
                    return noventaPorcento;
                }
                case 2 -> {
                    return noventaECincoPorcento;
                }
                case 3 -> {
                    return noventaENovePorcento;
                }
            }
        }else {
            System.out.println("ERRO! OPÇÃO INVALIDA, TENTE NOVAMENTE!");
            intervalosDeConfianca(0);
        }
        return -1;
    }

    public double margemDeErroE(ArrayList<Double> dadosAComputadr, double intervaloDeConfianca, int opcao){
        /*
        Dado um nível de confiança c, a margem de erro E às vezes chamada também de erro máximo da estimativa ou tolerância de erro)
         é a maior distância possível entre a estimativa pontual e o valor do parâmetro que ela está estimando.
         Para uma média populacional μ em que σ é conhecido.
         */
        Auxiliar aux = new Auxiliar();

        double desvioPadrao;
        int quantidadeDeAmostras = dadosAComputadr.size();

        switch (opcao){
            case 1:
                desvioPadrao = aux.lerDouble("Insira o valor do desvio padrão: ");
                return intervaloDeConfianca * (desvioPadrao/Math.sqrt(quantidadeDeAmostras));

            case 2:
                models.Formulas formulas = new models.Formulas();
                desvioPadrao = formulas.desvioPadraoAmostral(formulas.quadrados(formulas.desvios(dadosAComputadr)));
                System.out.println("O ALGORITMO IRÁ USAR UM DESVIO DE: " + desvioPadrao);
                return intervaloDeConfianca * (desvioPadrao/Math.sqrt(quantidadeDeAmostras));
        }

        return -1;
    }

    public double calculaMedia(ArrayList<Double> arr, int quantidadeDeAmostras){
        double resultado = 0;
        for (Double valor : arr){
            resultado += valor;
        }
        return resultado / quantidadeDeAmostras;
    }

    public void intervaloDeConfiancaParaMediaPopulacional(double media, double margemDeErro){
        Auxiliar aux = new Auxiliar();
        double limiteSuperior = limiteSuperior(media, margemDeErro);
        double limiteInferior = limiteInferior(media, margemDeErro);

        System.out.println(aux.formatarNumeroTresCasasDecimais(limiteInferior) + " < " + 'μ' + " < " + aux.formatarNumeroTresCasasDecimais(limiteSuperior));
    }

    public double limiteSuperior(double media, double margemDeErro){
        return media + margemDeErro;
    }

    public double limiteInferior(double media, double margemDeErro){
        return media - margemDeErro;
    }

    public double margemDeErroComParametros(double quantidadeDeAmostras, double desvioPadrao, double intervaloDeConfianca){
        return intervaloDeConfianca * (desvioPadrao/Math.sqrt(quantidadeDeAmostras));
    }

    public double tamanhoMinimoDaAmostra(double intervaloDeConfianca, double desvioPadrao, double margemDeErro){
        return Math.pow(((intervaloDeConfianca * desvioPadrao) / margemDeErro),2);
    }

    public int grausDeLiberdade(int tamanhoDaAmostra){
        return tamanhoDaAmostra -1;
    }

    public double soma(ArrayList<Double> arr){
        double resultado = 0;
        for (Double valor : arr){
            resultado += valor;
        }
        return resultado;
    }

    public double valorCritico(double grausDeLiberdade, double nivelDeConfianca){
        TDistribution tDistribution = new TDistribution(grausDeLiberdade);
        return tDistribution.inverseCumulativeProbability(1 - (1 - nivelDeConfianca) / 2);
    }

}
