package intervalosdeconfianca.media;

import auxiliar.Auxiliar;

import java.io.File;
import java.util.ArrayList;

public class QuestoesDaAula {
    static File tempDir = new File(System.getProperty("user.dir") + File.separator + "temp");
    File mediaPonderadaTemp = new File("temp/tempMediaPonderada");

    public static void main(String[] args) {
        //questaoUm();
        //questaoDois();
        //questaoTres();
        //questaoQuatro();
        //questaoCinco();
        //questaoSeis();
    }
    public static void questaoUm(){
        Formulas formulas = new Formulas();
        Auxiliar aux = new Auxiliar();
        ArrayList<Double> dadosAComputadr = aux.selecionaERecuperaArray(tempDir);

        double intervaloDeConfianca = formulas.intervalosDeConfianca(0);
        double media = formulas.calculaMedia(dadosAComputadr, 40);
        double margemDeErro = formulas.margemDeErroE(dadosAComputadr, intervaloDeConfianca, 1);

        System.out.println("MÉDIA: " + aux.formatarNumeroUmaCasaDecimal(media));
        System.out.println("MARGEM DE ERRO: " + aux.formatarNumeroUmaCasaDecimal(margemDeErro));
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro);
    }

    public static void questaoDois(){
        Formulas formulas = new Formulas();
        Auxiliar aux = new Auxiliar();

        double tamanhoAmostra = 20;
        double media = 22.9;
        double desvioPadrao = 1.5;
        double intervaloDeConfianca = formulas.intervalosDeConfianca(1);
        double margemDeErro = formulas.margemDeErroComParametros(tamanhoAmostra,desvioPadrao,intervaloDeConfianca);
        System.out.println("MARGEM DE ERRO: " + aux.formatarNumeroUmaCasaDecimal(margemDeErro));
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro);
    }

    public static void questaoTres(){
        Formulas formulas = new Formulas();
        Auxiliar aux = new Auxiliar();

        ArrayList<Double> dadosAComputadr = aux.selecionaERecuperaArray(tempDir);
        double mediaPopulacionalMeta = 1.5;
        double intervaloDeConfianca = formulas.intervalosDeConfianca(2);
        double desvioPadrao = 7.9;
        System.out.println("Tamanho mínimo da amostra: " + Math.ceil(formulas.tamanhoMinimoDaAmostra(intervaloDeConfianca,desvioPadrao,mediaPopulacionalMeta)));
    }

    public static void questaoQuatro(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();

        double nivelDeConfianca = 0.95;
        int tamanhoDaAmostra = 15;
        int grausDeLiberdade = formulas.grausDeLiberdade(tamanhoDaAmostra);

        System.out.println("t = ± "+aux.formatarNumeroTresCasasDecimais(formulas.valorCritico(grausDeLiberdade,nivelDeConfianca)));
    }

    public static void questaoCinco(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();

        int tamanhoAmostra = 16;
        double media = 162;
        double desvioPadrao = 10;
        double intervaloDeConfianca = 0.95;

        int grausDeLiberdade = formulas.grausDeLiberdade(tamanhoAmostra);
        double valorCritico = formulas.valorCritico(grausDeLiberdade, intervaloDeConfianca);
        double margemDeErro = formulas.margemDeErroComParametros(tamanhoAmostra,desvioPadrao,valorCritico);


        System.out.println("TAMANHO DA AMOSTRA: "+tamanhoAmostra);
        System.out.println("MÉDIA DA AMOSTRA: "+media);
        System.out.println("DESVIO PADRÃO: "+desvioPadrao);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfianca + "(" + (intervaloDeConfianca*100)+" %)");
        System.out.println("GRAUS DE LIBERDADE: "+grausDeLiberdade);
        System.out.println("VALOR CRÍTICO = " +aux.formatarNumeroTresCasasDecimais(valorCritico));
        System.out.println("MARGEM DE ERRO = " + aux.formatarNumeroUmaCasaDecimal(margemDeErro));
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro);
    }

    public static void questaoSeis(){
        Auxiliar aux = new Auxiliar();
        Formulas formulas = new Formulas();

        int tamanhoDaAmostra = 36;
        double media = 9.75;
        double desvioPadrao = 2.39;
        double intervaloDeConfianca = 0.99;

        int grausDeLiberdade = formulas.grausDeLiberdade(tamanhoDaAmostra);
        double valorCritico = formulas.valorCritico(grausDeLiberdade,intervaloDeConfianca);
        double margemDeErro = formulas.margemDeErroComParametros(tamanhoDaAmostra,desvioPadrao,valorCritico);

        System.out.println("TAMANHO DA AMOSTRA: "+tamanhoDaAmostra);
        System.out.println("MÉDIA DA AMOSTRA: "+media);
        System.out.println("DESVIO PADRÃO: "+desvioPadrao);
        System.out.println("INTERVALO DE CONFIANÇA: " + intervaloDeConfianca + "(" + (intervaloDeConfianca*100)+" %)");
        System.out.println("GRAUS DE LIBERDADE: "+grausDeLiberdade);
        System.out.println("VALOR CRÍTICO = " +aux.formatarNumeroTresCasasDecimais(valorCritico));
        System.out.println("MARGEM DE ERRO = " + aux.formatarNumeroDuasCasasDecimais(margemDeErro));
        formulas.intervaloDeConfiancaParaMediaPopulacional(media,margemDeErro);
    }
}
