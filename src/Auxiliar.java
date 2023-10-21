import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Auxiliar {
    public double lerDouble(String texto){
        double valorDouble = 0;
        System.out.print(texto);
        Scanner scanner = new Scanner(System.in);
        try {
            valorDouble = scanner.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("ERRO! VALOR DIGITADO NÃO É UM NÚMERO INTEIRO! DIGITE O VALOR NOVAMENTE");
            valorDouble = lerDouble(texto);
        }
        return valorDouble;
    }

    public int lerInt(String texto){
        int valorInt = 0;
        System.out.print(texto);
        Scanner scanner = new Scanner(System.in);
        try {
            valorInt = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("ERRO! VALOR DIGITADO NÃO É UM NÚMERO INTEIRO! DIGITE O VALOR NOVAMENTE");
            valorInt = lerInt(texto);
        }
        return valorInt;
    }

    public String lerString(String texto){
        String valorTexto;
        System.out.print(texto);
        Scanner scanner = new Scanner(System.in);
        try {
            valorTexto = scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("ERRO! VALOR DIGITADO NÃO É UM TEXTO! DIGITE O TEXTO NOVAMENTE");
            valorTexto = lerString(texto);
        }
        return valorTexto;
    }

    public void imprimirBonitinho(int numeroDeClasses, ArrayList<Double> limitesInferiores, ArrayList<Double> limitesSuperiores, ArrayList<Double> frequencias, ArrayList<Double> pontosMedios, ArrayList<Double> frequenciasRelativas, ArrayList<Double> frequenciasAcumuladas){
        System.out.println("Limites Inferiores | Limites Superiores | Frequências | Pontos Médios | Frequências Relativas | Frequências Acumuladas");

        for (int i = 0; i < numeroDeClasses; i++) {
            System.out.printf("%-18.2f | %-18.2f | %-11.2f | %-13.2f | %-21.2f | %-20.2f\n",
                    limitesInferiores.get(i), limitesSuperiores.get(i), frequencias.get(i), pontosMedios.get(i),
                    frequenciasRelativas.get(i), frequenciasAcumuladas.get(i));
        }
    }

    public void imprimirValoresDesviosEQuadrados(ArrayList<Double> valores, ArrayList<Double> desvios, ArrayList<Double> quadrados) {
        System.out.println("+----------+----------+----------+");
        System.out.println("| Valor    | Desvios  | Quadrados|");
        System.out.println("+----------+----------+----------+");

        double somaValores = 0;
        double somaDesvios = 0;
        double somaQuadrados = 0;
        for (int i = 0; i < valores.size(); i++) {
            System.out.printf("| %-8.2f | %-8.2f | %-8.2f |%n", valores.get(i), desvios.get(i), quadrados.get(i));
            somaValores += valores.get(i);
            somaDesvios += desvios.get(i);
            somaQuadrados += quadrados.get(i);
        }
        System.out.printf("| \u03A3%.2f  | \u03A3%.2f    | \u03A3%.2f   |%n", somaValores,somaDesvios,somaQuadrados);

        System.out.println("+----------+----------+----------+");
    }

    public void imprimeCoisasAmostraisComPesos(ArrayList<ValorPeso> valorEPeso, ArrayList<Double> valorXFrequencia, ArrayList<Double> valorMenosMedia, ArrayList<Double> issoAoQuadrado, ArrayList<Double> issoVezesFrequencia){
        System.out.println("+----------+--------+------+-------+----------------+------------------+");
        System.out.println("|x         |f       |xf    |x - u  |(x - u)Quadrado |((x - u)Quadrado)f ");
        System.out.println("+----------+--------+------+-------+----------------+------------------+");

        for (int i = 0; i < valorEPeso.size(); i++){
            System.out.printf("|%-9.2f |%-7.2f |%-5.2f |%-6.2f |%-15.2f |%-8.2f          |%n", valorEPeso.get(i).getValor(), valorEPeso.get(i).getPeso(), valorXFrequencia.get(i), valorMenosMedia.get(i), issoAoQuadrado.get(i), issoVezesFrequencia.get(i));
        }
        System.out.println("+----------+--------+------+-------+----------------+------------------+");
    }

}
