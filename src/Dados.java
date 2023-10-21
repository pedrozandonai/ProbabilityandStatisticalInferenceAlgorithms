import java.io.*;
import java.util.ArrayList;

public class Dados {
    public void criaArrDoubleEGrava(int quantidadeDeDados, File tempDir) {
        Auxiliar aux = new Auxiliar();
        ArrayList<Double> arr = new ArrayList<>();

        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        try {
            String nomeDoArquivo = aux.lerString("Digite um nome para o arquivo a ser salvo temporariamente: ");
            FileOutputStream arq = new FileOutputStream(tempDir + File.separator + nomeDoArquivo + ".dat");
            DataOutputStream gravarArq = new DataOutputStream(arq);

            for (int i = 0; i < quantidadeDeDados; i++) {
                double dado = aux.lerDouble("Digite o valor " + (i + 1) + ": ");
                arr.add(dado);
                gravarArq.writeDouble(dado);
            }

            gravarArq.close();
            arq.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> recuperaDadosArrDouble(int quantidadeDeDados, String caminhoDoArquivo) {
        ArrayList<Double> arr = new ArrayList<>();

        try {
            FileInputStream arq = new FileInputStream(caminhoDoArquivo);
            DataInputStream lerArq = new DataInputStream(arq);

            for (int i = 0; i < quantidadeDeDados; i++) {
                Double dado = lerArq.readDouble();
                arr.add(dado);
            }

            arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void criaArrDoubleEGravaParaMediaPonderada(int quantidadeDeDados, File tempDir) {
        Auxiliar aux = new Auxiliar();
        ArrayList<ValorPeso> arr = new ArrayList<>();

        File novaPasta = new File(tempDir + File.separator + "tempMediaPonderada");
        if (!novaPasta.exists()) {
            novaPasta.mkdir();
        }

        try {
            String nomeDoArquivo = aux.lerString("Digite um nome para o arquivo a ser salvo temporariamente: ");
            FileOutputStream arq = new FileOutputStream(novaPasta + File.separator + nomeDoArquivo + ".dat");
            DataOutputStream gravarArq = new DataOutputStream(arq);

            for (int i = 0; i < quantidadeDeDados; i++) {
                double dado = aux.lerDouble("Digite o valor " + (i + 1) + ": ");
                double peso = aux.lerDouble("Digite o peso para o valor " + (i + 1) + ": ");
                arr.add(new ValorPeso(dado, peso));
                gravarArq.writeDouble(dado);
                gravarArq.writeDouble(peso);
            }

            gravarArq.close();
            arq.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ValorPeso> recuperaDadosArrDoubleParaMediaPonderada(int quantidadeDeDados, String caminhoDoArquivo) {
        ArrayList<ValorPeso> arr = new ArrayList<>();

        try {
            FileInputStream arq = new FileInputStream(caminhoDoArquivo);
            DataInputStream lerArq = new DataInputStream(arq);

            while (lerArq.available() > 0) {
                double dado = lerArq.readDouble();
                double peso = lerArq.readDouble();
                ValorPeso valorPeso = new ValorPeso(dado, peso);
                arr.add(valorPeso);
            }

            arq.close();
        } catch (EOFException eof) {
            // Tratar o fim do arquivo, se necessário
            eof.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

    // DELETA A PASTA TEMPORÁRIA UTILIZANDO RECURSÃO
    public void deleteTempDirectoryOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File tempDir = new File(System.getProperty("user.dir") + File.separator + "temp");
            deleteDirectory(tempDir);
        }));
    }

    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        dir.delete();
    }

    // VERIFICA SE O ARQUIVO EXISTE E CASO NÃO EXISTE REALIZA A RECURSÃO
    public File verificaSeArquivoExiste(int escolhaUsuario, File dir) {
        Auxiliar aux = new Auxiliar();
        String[] conteudosDoDiretorio = dir.list();
        if (escolhaUsuario <= conteudosDoDiretorio.length) {
            File arquivoSelecionado = new File(dir.getAbsolutePath() + File.separator + conteudosDoDiretorio[escolhaUsuario - 1]);
            if (arquivoSelecionado.exists() && arquivoSelecionado.isFile()) {
                return arquivoSelecionado;
            }
        } else {
            System.out.println("ERRO! ARQUIVO INEXISTENTE, DIGITE NOVAMENTE!");
            escolhaUsuario = aux.lerInt("Digite novamente a opção: ");
            return verificaSeArquivoExiste(escolhaUsuario, dir);
        }
        return null;
    }

    // Varre o arquivo e retorna a contagem de quantos dados ele possui.
    public int contaQuantidadeDeDados(File arquivo) {
        int quantidadeDeDados = 0;

        try {
            if (arquivo.getName().endsWith(".dat")) {
                FileInputStream fileInputStream = new FileInputStream(arquivo);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

                while (true) {
                    try {
                        dataInputStream.readDouble();
                        quantidadeDeDados++;
                    } catch (EOFException e) {
                        break;
                    }
                }

                dataInputStream.close();
                bufferedInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quantidadeDeDados;
    }



}
