package models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Formulas {
    public double calcularAmplitude(ArrayList<Double> arr, int numeroDeClasses) {
        if (arr != null && !arr.isEmpty()) {

            double valorMaximo = Collections.max(arr);
            double valorMinimo = Collections.min(arr);

            double amplitude = (valorMaximo - valorMinimo) / numeroDeClasses;

            return Math.ceil(amplitude);
        } else {
            throw new IllegalArgumentException("O ArrayList está vazio ou nulo.");
        }
    }

    public ArrayList<Double> limitesInferiores(ArrayList<Double> arr, int numeroDeClasses) {
        ArrayList<Double> limitesInferiores = new ArrayList<>();
        double amplitude = calcularAmplitude(arr, numeroDeClasses);
        double limiteInferior = Collections.min(arr);
        limitesInferiores.add(limiteInferior);

        for (int i = 1; i < numeroDeClasses; i++) {
            limiteInferior += amplitude;
            limitesInferiores.add(limiteInferior);
        }

        return limitesInferiores;
    }

    public ArrayList<Double> limitesSuperiores(ArrayList<Double> limitesInferiores, ArrayList<Double> arr, int numeroDeClasses) {
        ArrayList<Double> limitesSuperiores = new ArrayList<>();
        double amplitude = calcularAmplitude(arr, numeroDeClasses);
        double limiteSuperior = (limitesInferiores.get(1) - 1);
        limitesSuperiores.add(0,limiteSuperior);

        for (int i = 1; i < numeroDeClasses; i++) {
            limiteSuperior += amplitude;
            limitesSuperiores.add(limiteSuperior);
        }

        return limitesSuperiores;
    }


    public ArrayList<Double> calcularFrequencia(ArrayList<Double> arr, ArrayList<Double> limitesInferiores, ArrayList<Double> limitesSuperiores) {
        ArrayList<Double> frequencias = new ArrayList<>();

        for (int i = 0; i < limitesInferiores.size(); i++) {
            double limiteInferior = limitesInferiores.get(i);
            double limiteSuperior = limitesSuperiores.get(i);
            int contador = 0;

            for (Double valor : arr) {
                if (valor >= limiteInferior && valor < limiteSuperior) {
                    contador++;
                }
            }
            frequencias.add((double) contador);
        }

        return frequencias;
    }

    public ArrayList<Double> calcularPontoMedio(ArrayList<Double> limitesInferiores, ArrayList<Double> limitesSuperiores){
        ArrayList<Double> pontosMedios = new ArrayList<>();

        for (int i = 0; i < limitesInferiores.size(); i++){
            double limiteInferior = limitesInferiores.get(i);
            double limiteSuperior = limitesSuperiores.get(i);

            double pontoMedio = (limiteInferior + limiteSuperior) / 2 ;
            pontosMedios.add(pontoMedio);
        }

        return pontosMedios;
    }

    public ArrayList<Double> calcularFrequenciasRelativas(ArrayList<Double> frequencias, int tamanhoDaAmostra){
        ArrayList<Double> frequenciasRelativas = new ArrayList<>();

        // Criar um formato para duas casas decimais com a configuração regional apropriada
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

        for (int i = 0; i < frequencias.size(); i++){
            double frequencia = frequencias.get(i);

            double frequenciaRelativa = frequencia / tamanhoDaAmostra;
            // Formatando o resultado para duas casas decimais
            String formattedResult = df.format(frequenciaRelativa);
            frequenciasRelativas.add(Double.parseDouble(formattedResult.replace(',', '.')));
        }

        return frequenciasRelativas;
    }

    public ArrayList<Double> calcularFrequenciasAcumuladas(ArrayList<Double> frequencias){
        ArrayList<Double> frequenciasAcumuladas = new ArrayList<>();
        double valor = frequencias.get(0);
        frequenciasAcumuladas.add(valor);

        for (int i = 1; i < frequencias.size(); i++){
            valor += frequencias.get(i);
            frequenciasAcumuladas.add(valor);
        }

        return frequenciasAcumuladas;
    }

    public static double calcularMedia(ArrayList<Double> arrayList) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator(' '); // Definindo o separador de milhares como espaço vazio
        DecimalFormat df = new DecimalFormat("#.0", otherSymbols);

        int elementos = arrayList.size();
        double soma = 0;

        for (Double elemento : arrayList) {
            soma += elemento;
        }
        double media = soma / elementos;

        return Double.parseDouble(df.format(media).replace(',', '.'));
    }

    public double calcularMediana(ArrayList<Double> arrayList) {
        Collections.sort(arrayList);

        int tamanho = arrayList.size();
        if (tamanho % 2 != 0) {
            return arrayList.get(tamanho / 2);
        } else {
            double mediana1 = arrayList.get((tamanho - 1) / 2);
            double mediana2 = arrayList.get(tamanho / 2);
            return (mediana1 + mediana2) / 2;
        }
    }

    public double calcularModa(ArrayList<Double> array) {
        Map<Double, Integer> frequenciaMap = new HashMap<>();

        for (Double elemento : array) {
            frequenciaMap.put(elemento, frequenciaMap.getOrDefault(elemento, 0) + 1);
        }

        double moda = 0;
        int maxFrequencia = 0;

        for (Map.Entry<Double, Integer> entry : frequenciaMap.entrySet()) {
            if (entry.getValue() > maxFrequencia) {
                moda = entry.getKey();
                maxFrequencia = entry.getValue();
            }
        }

        return moda;
    }

    public static double calcularMediaPonderada(ArrayList<ValorPeso> arr) {
        double somaProduto = 0;
        double somaPesos = 0;

        for (int i = 0; i < arr.size(); i++) {
            somaProduto += arr.get(i).getValor() * arr.get(i).getPeso();
            somaPesos += arr.get(i).getPeso();
        }

        if (somaPesos == 0) {
            throw new ArithmeticException("A soma dos pesos não pode ser zero.");
        }

        return somaProduto / somaPesos;
    }

    public ArrayList<Double> desvios(ArrayList<Double> arrayList){
        ArrayList<Double> desvios = new ArrayList<>();
        double media = calcularMedia(arrayList);
        for (Double valor : arrayList){
            double resultado = valor - media;
            desvios.add(resultado);
        }
        return desvios;
    }

    public static ArrayList<Double> quadrados(ArrayList<Double> desvios){
        ArrayList<Double> quadrados = new ArrayList<>();

        for (Double valor : desvios){
            double resultado = Math.pow(valor,2);
            quadrados.add(resultado);
        }

        return quadrados;
    }

    public static double varianciaPopulacional(ArrayList<Double> quadrados, double amplitude){
        double somaDosQuadrados = 0;
        for (Double quadrado : quadrados){
            somaDosQuadrados += quadrado;
        }

        return Math.round(somaDosQuadrados/amplitude);
    }

    public static double desvioPadraoPopulacional(ArrayList<Double> quadrados, double amplitude){
        double varianciaPopulacional = varianciaPopulacional(quadrados, amplitude);
        return Math.round(Math.sqrt(varianciaPopulacional));
    }

    public static double varianciaAmostral(ArrayList<Double> quadrados){
        int quantidadeDeElementos = quadrados.size();
        double somaDosQuadrados = 0;
        for (Double quadrado : quadrados){
            somaDosQuadrados += quadrado;
        }

        return Math.round(somaDosQuadrados/(quantidadeDeElementos-1));
    }

    public static double desvioPadraoAmostral(ArrayList<Double> quadrados){
        double varianciaAmostral = varianciaAmostral(quadrados);
        return Math.round(Math.sqrt(varianciaAmostral));
    }

    public static ArrayList<Double> valoresVezesFrequencias(ArrayList<ValorPeso> valorPeso){
        ArrayList<Double> valoresVezesFrequencias = new ArrayList<>();
        for (int i = 0; i < valorPeso.size(); i++){
            double resultados = valorPeso.get(i).getValor() * valorPeso.get(i).getPeso();
            valoresVezesFrequencias.add(resultados);
        }
        return valoresVezesFrequencias;
    }

    public static double somaDasFrequencias(ArrayList<ValorPeso> valorPeso){
        double somaAsFrequencias = 0;
        for (ValorPeso valorePeso : valorPeso){
            somaAsFrequencias += valorePeso.getPeso();
        }
        return somaAsFrequencias;
    }

    public static double mediaAmostral(ArrayList<ValorPeso> valorPeso){
        ArrayList<Double> valorFrequencia = valoresVezesFrequencias(valorPeso);
        double resultados = 0;
        for (Double valor : valorFrequencia){
            resultados += valor;
        }

        double somaFrequencias = somaDasFrequencias(valorPeso);
        return (resultados/somaFrequencias);
    }

    public static ArrayList<Double> valorMenosMedia(ArrayList<ValorPeso> valorPeso){
        ArrayList<Double> valorMenosMedia = new ArrayList<>();
        double mediaAmostral = mediaAmostral(valorPeso);
        for (ValorPeso valorEPeso : valorPeso){
            double resultado = valorEPeso.getValor() - mediaAmostral;
            valorMenosMedia.add(resultado);
        }
        return valorMenosMedia;
    }

    public static ArrayList<Double> quadradoVezesFrequencia(ArrayList<ValorPeso> valorPesos){
        ArrayList<Double> quadradoVezesFrequencia = new ArrayList<>();
        ArrayList<Double> valorMensMedia = valorMenosMedia(valorPesos);
        ArrayList<Double> quadrados = quadrados(valorMensMedia);

        for (int i = 0; i < valorPesos.size(); i++){
            double resultados = quadrados.get(i) * valorPesos.get(i).getPeso();
            quadradoVezesFrequencia.add(resultados);
        }
        return quadradoVezesFrequencia;
    }

    public static double desvioPadraoAmostralComPesos(ArrayList<ValorPeso> valorPesos){
        ArrayList<Double> quadradoVezesFrequencia = quadradoVezesFrequencia(valorPesos);
        double amostrasMenosUm = somaDasFrequencias(valorPesos) - 1;
        double somatoria = 0;
        for (Double valor : quadradoVezesFrequencia){
            somatoria += valor;
        }
        double resultado = Math.sqrt(somatoria/amostrasMenosUm);
        return Math.round(resultado * 100.0) / 100.0;
    }
}
