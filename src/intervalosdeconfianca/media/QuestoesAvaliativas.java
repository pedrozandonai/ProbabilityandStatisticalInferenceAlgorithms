package intervalosdeconfianca.media;

import auxiliar.Auxiliar;
import models.Dados;

import java.io.File;
import java.util.ArrayList;


public class QuestoesAvaliativas {
    static File tempDir = new File(System.getProperty("user.dir") + File.separator + "temp");

    public static void main(String[] args) {
        //questaoUm();
        questaoDois();
        questaoTres();
    }

    public static void questaoUm(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();

        int tamanhoDaAmostra = 48;
        double media = 3.63;
        double desvioPadrao = 0.21;
        double intervaloDeConfiancaUm = 0.90;
        double intervaloDeConfiancaDois = 0.95;
        int grausDeLiberdade = formulas.grausDeLiberdade(tamanhoDaAmostra);

        System.out.println("DADOS FORNECIDOS PELO PROBLEMA OU INFERENCIADOS: ");
        System.out.println("TAMANHO DA AMOSTRA: "+tamanhoDaAmostra);
        System.out.println("MÉDIA DA AMOSTRA: "+media);
        System.out.println("DESVIO PADRÃO: "+desvioPadrao);
        System.out.println("GRAUS DE LIBERDADE: "+grausDeLiberdade);

        // Utilizando 90% ou 0.90
        System.out.println("-----------------------90%------------------------");
        double valorCritico = formulas.valorCritico(grausDeLiberdade,intervaloDeConfiancaUm);
        double margemDeErro = formulas.margemDeErroComParametros(tamanhoDaAmostra,desvioPadrao,valorCritico);

        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfiancaUm + "(" + (intervaloDeConfiancaUm*100)+" %)");
        System.out.println("VALOR CRÍTICO = " +aux.formatarNumeroTresCasasDecimais(valorCritico));
        System.out.println("MARGEM DE ERRO = " + aux.formatarNumeroDuasCasasDecimais(margemDeErro));
        System.out.println("INTERVALOS DE CONFIANÇA (LIMITE INFERIOR E SUPERIOR)");
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro);


        // Utilizando 95% ou 0.95
        System.out.println("-----------------------95%------------------------");
        double valorCritico2 = formulas.valorCritico(grausDeLiberdade,intervaloDeConfiancaDois);
        double margemDeErro2 = formulas.margemDeErroComParametros(tamanhoDaAmostra,desvioPadrao,valorCritico2);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfiancaDois + "(" + (intervaloDeConfiancaDois*100)+" %)");
        System.out.println("VALOR CRÍTICO = " +aux.formatarNumeroTresCasasDecimais(valorCritico2));
        System.out.println("MARGEM DE ERRO = " + aux.formatarNumeroDuasCasasDecimais(margemDeErro2));
        System.out.println("INTERVALOS DE CONFIANÇA (LIMITE INFERIOR E SUPERIOR)");
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro2);

    }

    public static void questaoDois(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();

        double margemDeErroMaxima = 0.1;

        System.out.println("DADOS FORNECIDOS PELO PROBLEMA OU INFERENCIADOS: ");
        System.out.println("MARGEM DE ERRO MÁXIMA PERMITIDA: " +margemDeErroMaxima);
        System.out.println();

        System.out.println("questao a)");
        double desvioPadraoPopulacional = 0.25;
        double intervaloDeConfianca = 0.99;
        double intervaloDeConfiancaNoEscoreZ = formulas.intervalosDeConfianca(3);
        System.out.println("DADOS FORNECIDOS PELO PROBLEMA OU INFERENCIADOS: ");
        System.out.println("DESVIO PADRÃO POPULACIONAL: "+desvioPadraoPopulacional);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfianca + "(" + (intervaloDeConfianca*100)+" %)");
        System.out.println();

        System.out.println("RESPOSTA:\nTAMANHO MÍNIMO DA AMOSTRA: " +Math.ceil(formulas.tamanhoMinimoDaAmostra(intervaloDeConfiancaNoEscoreZ,desvioPadraoPopulacional,margemDeErroMaxima)));

        System.out.println();
        System.out.println("questao b)");
        double desvioPadraoPopulacionalB = 0.3;
        System.out.println("DADOS FORNECIDOS PELO PROBLEMA OU INFERENCIADOS: ");
        System.out.println("DESVIO PADRÃO POPULACIONAL: "+desvioPadraoPopulacional);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfianca + "(" + (intervaloDeConfianca*100)+" %)");
        System.out.println();

        System.out.println("RESPOSTA:\nTAMANHO MÍNIMO DA AMOSTRA: " +Math.ceil(formulas.tamanhoMinimoDaAmostra(intervaloDeConfiancaNoEscoreZ,desvioPadraoPopulacionalB,margemDeErroMaxima)));

        System.out.println();
        System.out.println("questao c)");
        System.out.println("O DESVIO PADRÃO QUE REQUER UMA AMOSTRA MAIOR É O DA LETRA B (0.3), POIS ");
    }

    public static void questaoTres(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();
        models.Formulas formulas1 = new models.Formulas();
        ArrayList<Double> dadosAComputar = aux.selecionaERecuperaArray(tempDir);

        int tamanhoDaAmostra = 35;
        System.out.println("DADOS FORNECIDOS PELO PROBLEMA OU INFERENCIADOS: ");
        System.out.println("TAMANHO DA AMOSTRA: " +tamanhoDaAmostra);
        System.out.println();

        System.out.println("questao a) MÉDIA AMOSTRAL");
        double mediaAmostral = formulas.calculaMedia(dadosAComputar, tamanhoDaAmostra);
        System.out.println("RESPOSTA:");
        System.out.println("RESULTADO DA SOMA DE TODOS BAGUIO: " + formulas.soma(dadosAComputar));
        System.out.println(mediaAmostral);
        System.out.println();

        System.out.println("questao b) DESVIO PADRÃO AMOSTRAL");
        double desvioPadraoAmostral = formulas1.desvioPadraoAmostral(formulas1.quadrados(formulas1.desvios(dadosAComputar)));
        System.out.println("RESPOSTA:");
        System.out.println(desvioPadraoAmostral);
        System.out.println();

        System.out.println("questao c) INTERVALO DE CONFIANÇA DE 98% PARA MEDIA POPULACIONAL");
        double intervaloDeConfianca = 0.98;
        int grausDeLiberdade = formulas.grausDeLiberdade(tamanhoDaAmostra);
        double valorCritico = formulas.valorCritico(grausDeLiberdade,intervaloDeConfianca);
        double margemDeErro = formulas.margemDeErroComParametros(tamanhoDaAmostra,desvioPadraoAmostral,valorCritico);

        System.out.println("TAMANHO DA AMOSTRA: "+tamanhoDaAmostra);
        System.out.println("MÉDIA DA AMOSTRA: "+mediaAmostral);
        System.out.println("DESVIO PADRÃO: "+desvioPadraoAmostral);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfianca + "(" + (intervaloDeConfianca*100)+" %)");
        System.out.println("GRAUS DE LIBERDADE: "+grausDeLiberdade);
        System.out.println("VALOR CRÍTICO = " +aux.formatarNumeroTresCasasDecimais(valorCritico));
        System.out.println("MARGEM DE ERRO = " + aux.formatarNumeroDuasCasasDecimais(margemDeErro));

        System.out.println("RESPOSTA:");
        formulas.intervaloDeConfiancaParaMediaPopulacional(mediaAmostral,margemDeErro);

    }
}
