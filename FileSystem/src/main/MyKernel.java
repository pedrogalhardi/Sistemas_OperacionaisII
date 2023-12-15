package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import static binary.Binario.binaryStringToInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import operatingSystem.Kernel;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kernel desenvolvido pelo aluno. Outras classes criadas pelo aluno podem ser
 * utilizadas, como por exemplo: - Arvores; - Filas; - Pilhas; - etc...
 *
 * @author pedro
 */
public class MyKernel implements Kernel {

    Diretorio d = new Diretorio("Raiz");
    ArrayList<Arquivo> a = new ArrayList<Arquivo>();
    int index = 0;
    int atual = 0;
    int indexa = 0;
    boolean raiz = true;

    /*Diretorio raiz = new Diretorio(null);
    Diretorio dirAtual = new Diretorio(raiz);
    HardDisk HD = new HardDisk(4);
    int positionHD, positionDirAtual;

    public int verificaOrigem(String Caminho[], boolean finalCaminho) {
        int dirTemp;

        if (Caminho == null) {
            return -1;
        } else {
            switch (Caminho[0]) {
                case "":
                    dirTemp = 0;
                    break;
                case "..":
                    String binario = retornaBinario(positionDirAtual + 880, (positionDirAtual + 880 + 16));
                    dirTemp = binaryStringToInt(binario);
                    break;
                default:
                    dirTemp = positionDirAtual;
                    break;
            }
        }
        if (dirTemp >= 0) {
            dirTemp = percorreCaminho(dirTemp, Caminho, finalCaminho);
        }
        return dirTemp;
    }

    public int percorreCaminho(int dirTemp, String[] Caminho, boolean finalCaminho) {
        int diretorio = dirTemp, limite, i = 0, positionFilhos, limiteDiretorio;
        boolean nomeEncontrado = false;
        String nome;

        if (finalCaminho) {
            limite = Caminho.length;
        } else {
            limite = Caminho.length - 1;
        }

        if (Caminho.length == 1) {
            return diretorio;
        }

        while (i < limite) {

            if (Caminho[i].equals("..")) {
                String binario = retornaBinario(positionDirAtual + 880, (positionDirAtual + 880 + 16));
                diretorio = binaryStringToInt(binario);
            } else if (Caminho[i].equals(".")) {
                diretorio = diretorio;
            } else if (Caminho[i].equals("")) {
                diretorio = 0;
            } else {
                limiteDiretorio = diretorio + 2496;
                diretorio += 896;
                while (diretorio < (limiteDiretorio) && !nomeEncontrado) {

                    String binario = retornaBinario(diretorio, (diretorio + 16));
                    positionFilhos = binaryStringToInt(binario);

                    nome = retornaString(positionFilhos, positionFilhos + (80 * 8));
                    if (Caminho[i].equals(nome)) {
                        diretorio = binaryStringToInt(binario);
                        nomeEncontrado = true;
                        dirTemp = diretorio;
                    } else {
                        diretorio += 16;
                        dirTemp = -1;
                    }
                }
                nomeEncontrado = false;
            }

            i++;
        }

        return dirTemp;
    }
    
        public void limpaHD(int posicaoInicio, int posicaoFinal) {
        int i = posicaoInicio, j = posicaoInicio;
        String bin = "";

        while (i < posicaoFinal) {
            HD.setBitDaPosicao(false, j);
            j++;
            i++;
        }
    }*/
    public String ls(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: ls");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //System.out.println("ola pessoal!");
        String s = "";
        for (int i = 0; i < d.getDiretorio().size(); i++) {
            result = result + " " + d.getDiretorio().get(i).getNome();
        }
        for (int i = 0; i < a.size(); i++) {
            s = a.get(i).getNome() + s;
        }
        result = "Diretorios:" + result + " Arquivos: " + s;
        //fim da implementacao do aluno
        return result;
    }

    public String mkdir(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: mkdir");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f = true;
        System.out.println(d.getDiretorio().size());
        for (int i = 0; i < d.getDiretorio().size(); i++) {
            String s = d.getDiretorio().get(i).getNome();
            if (parameters.equals(s)) {
                f = false;
                break;
            }
        }
        String[] s = parameters.split(" ");
        try {
            if (s[1] != "") {
                f = false;
            }
        } catch (Exception e) {

        }
        if (f) {
            d.getDiretorio().add(new Diretorio(parameters));
            if (index == 0) {
                d.setPai("");
                d.setFilho(d.getDiretorio().get(index).getNome());
                d.getDiretorio().get(index).setPai("Raiz");
                d.getDiretorio().get(index).setCaminho("");
                System.out.println(d.getFilho() + " " + d.getNome());
                System.out.println(d.getDiretorio().get(index).getCaminho());
                index++;
            } else {
                if (raiz) {
                    d.getDiretorio().get(index).setPai(d.getNome());
                    d.setFilho(d.getDiretorio().get(index).getNome());
                    d.getDiretorio().get(index).setCaminho("");
                    System.out.println(d.getFilho());
                    index++;
                } else {
                    d.getDiretorio().get(index).setPai(d.getDiretorio().get(atual).getNome());
                    d.getDiretorio().get(atual).setFilho(d.getDiretorio().get(index).getNome());
                    d.getDiretorio().get(index).setCaminho(d.getDiretorio().get(atual).getCaminho());
                    System.out.println(d.getDiretorio().get(atual).getFilho());
                    index++;
                }
            }
            result = "Diretorio " + parameters + " criado com Sucesso";
        } else {
            result = "Nome do Diretorio ja existente";
        }
        try {
            if (s[1] != "") {
                result = "Nome Invalido";
            }
        } catch (Exception e) {

        }
        //fim da implementacao do aluno
        return result;
    }

    public String cd(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        String currentDir = "";
        System.out.println("Chamada de Sistema: cd");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //indique o diretório atual. Por exemplo... /
        boolean f = false;
        for (int i = 0; i < d.getDiretorio().size(); i++) {
            String s = d.getDiretorio().get(i).getNome();
            if (parameters.equals(s)) {
                atual = i;
                f = true;
                break;
            }
        }
        if (parameters.equals("Raiz")) {
            currentDir = "/";
            result = "Posicao atual: " + d.getNome();
            raiz = true;
        } else {
            if (f) {
                currentDir = d.getDiretorio().get(atual).getCaminho();
                result = "Posicao atual: " + d.getDiretorio().get(atual).getNome();
                raiz = false;
            } else {
                result = "Diretorio ou Arquivo Inexistente";
            }
        }

        //setando parte gráfica do diretorio atual
        operatingSystem.fileSystem.FileSytemSimulator.currentDir = currentDir;

        //fim da implementacao do aluno
        return result;
    }

    public String rmdir(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: rmdir");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f = false;
        int pos = 0;

        for (int i = 0; i < d.getDiretorio().size(); i++) {
            String s = d.getDiretorio().get(i).getNome();
            if (parameters.equals(s)) {
                pos = i;
                f = true;
                break;
            }
        }
        if (f) {
            if (atual == pos) {
                result = "Nao eh possivel excluir o diretorio atual";
            } else {
                if (d.getDiretorio().get(pos).getFilho().isEmpty()) {
                    int pai = 0;
                    for (int i = 0; i < d.getDiretorio().size(); i++) {
                        String s = d.getDiretorio().get(i).getNome();
                        if (d.getDiretorio().get(pos).getPai().equals(s)) {
                            pai = i;
                            System.out.println("Pai = " + i);
                            break;
                        }
                    }
                    int filho = 0;
                    for (int i = 0; i < d.getDiretorio().get(pai).getFilho().size(); i++) {
                        String s = d.getDiretorio().get(pos).getNome();
                        if (d.getDiretorio().get(pai).getPFilho(i).equals(s)) {
                            filho = i;
                            System.out.println("Filho = " + i);
                            break;
                        }
                    }
                    d.getDiretorio().get(pai).removeFilho(filho);
                    d.getDiretorio().remove(pos);
                    index--;
                    result = "Diretorio excluido com sucesso";
                } else {
                    result = "O Diretorio nao esta vazio";
                }
            }
        } else {
            result = "Diretorio nao encontrado";
        }

        //fim da implementacao do aluno
        return result;
    }

    public String cp(String parameters) {
        // Variável result deverá conter o que vai ser impresso na tela após o comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: cp");
        System.out.println("\tParametros: " + parameters);

        // Verifique se o parâmetro não está vazio
        if (parameters.isEmpty()) {
            result = "Parâmetro inválido. Forneça o nome do arquivo ou diretório a ser copiado e o destino.";
        } else {
            String[] params = parameters.split(" ");

            if (params.length != 2) {
                result = "Número incorreto de parâmetros. Use o formato 'cp origem destino'.";
            } else {
                String origem = params[0];
                String destino = params[1];

                // Verifique se a origem corresponde a um arquivo
                Arquivo arquivoOrigem = null;
                for (Arquivo arquivo : a) {
                    if (arquivo.getNome().equals(origem)) {
                        arquivoOrigem = arquivo;
                        break;
                    }
                }

                // Verifique se a origem corresponde a um diretório
                Diretorio diretorioOrigem = null;
                for (Diretorio dir : d.getDiretorio()) {
                    if (dir.getNome().equals(origem)) {
                        diretorioOrigem = dir;
                        break;
                    }
                }

                // Verifique se o destino corresponde a um diretório existente
                Diretorio diretorioDestino = null;
                for (Diretorio dir : d.getDiretorio()) {
                    if (dir.getNome().equals(destino)) {
                        diretorioDestino = dir;
                        break;
                    }
                }

                if (arquivoOrigem != null && diretorioDestino != null) {
                    // Copiar o arquivo para o diretório de destino
                    Arquivo novoArquivo = new Arquivo(diretorioDestino.getNome(), arquivoOrigem.getConteudo(), arquivoOrigem.getNome(), arquivoOrigem.getExtencao(), arquivoOrigem.getPermissao());
                    diretorioDestino.criarArquivo(novoArquivo);
                    a.add(novoArquivo);
                    result = "Arquivo copiado com sucesso.";
                } else if (diretorioOrigem != null && diretorioDestino != null) {
                    // Copiar o diretório para o diretório de destino
                    Diretorio novoDiretorio = new Diretorio(diretorioDestino.getNome());
                    diretorioDestino.criarDiretorio(novoDiretorio);
                    novoDiretorio.setPai(diretorioDestino.getNome());
                    result = "Diretório copiado com sucesso.";
                } else {
                    result = "Origem ou destino não encontrado ou incorreto.";
                }
            }
        }

        return result;
    }

    public String mv(String parameters) {
        //variavel result deverá conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: mv");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        String[] s = parameters.split(" ");
        boolean dirf = false;
        boolean dirp = false;
        boolean arqf = false;
        boolean arqp = false;
        int posf = 0;
        int posp = 0;
        for (int i = 0; i < d.getDiretorio().size(); i++) {
            if (s[0].equals(d.getDiretorio().get(i).getNome())) {
                posf = i;
                dirf = true;
                break;
            }
        }
        if (dirf == false) {
            for (int i = 0; i < a.size(); i++) {
                if (s[0].equals(a.get(i).getNome())) {
                    posf = i;
                    arqf = true;
                    break;
                }
            }
        }
        if (dirf) {
            for (int i = 0; i < d.getDiretorio().size(); i++) {
                if (s[1].equals(d.getDiretorio().get(i).getNome())) {
                    posp = i;
                    dirp = true;
                    break;
                }
            }
            if (dirp) {

            } else {
                result = "Arquivo ou Diretorio nao encontrado";
            }
        } else if (arqf) {
            for (int i = 0; i < a.size(); i++) {
                if (s[0].equals(a.get(i).getNome())) {
                    posp = i;
                    arqp = true;
                    break;
                }
            }
            if (arqp) {
                d.getDiretorio().get(posf).setPai(d.getDiretorio().get(posp).getNome());
                d.getDiretorio().get(posf).setCaminho(d.getDiretorio().get(posp).getCaminho());
                for (int i = 0; i < d.getDiretorio().get(posf).getFilho().size(); i++) {
                    boolean f = false;
                    int p = 0;
                    String x = d.getDiretorio().get(posf).getPFilho(i);
                    for (int j = 0; j < d.getDiretorio().size(); j++) {
                        if (x.equals(d.getDiretorio().get(i).getNome())) {
                            f = true;
                            p = i;
                            break;
                        }
                    }
                }
            } else {
                result = "Arquivo ou Diretorio nao encontrado";
            }
        } else {
            result = "Arquivo ou Diretorio nao encontrado";
        }
        //fim da implementacao do aluno
        return result;
    }

    public String rm(String parameters) {
        //variavel result deverá conter o que será impresso na tela após o comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: rm");
        System.out.println("\tParametros: " + parameters);

        // Verifique se o parâmetro não está vazio
        if (parameters.isEmpty()) {
            result = "Parâmetro inválido. Forneça o nome do arquivo ou diretório a ser removido.";
        } else {
            // Verifique se o parâmetro começa com "./" e, se sim, remova o prefixo
            if (parameters.startsWith("./")) {
                parameters = parameters.substring(2);
            }

            boolean encontrado = false;

            // Verifique se o parâmetro corresponde a um arquivo
            for (int i = 0; i < a.size(); i++) {
                if (parameters.equals(a.get(i).getNome())) {
                    a.remove(i);
                    result = "Arquivo removido com sucesso.";
                    encontrado = true;
                    break;
                }
            }

            // Se não for um arquivo, verifique se corresponde a um diretório
            if (!encontrado) {
                for (int i = 0; i < d.getDiretorio().size(); i++) {
                    if (parameters.equals(d.getDiretorio().get(i).getNome())) {
                        // Verifique se o diretório está vazio antes de removê-lo
                        if (d.getDiretorio().get(i).getFilho().isEmpty()) {
                            d.getDiretorio().remove(i);
                            result = "Diretório removido com sucesso.";
                            encontrado = true;
                        } else {
                            result = "Não é possível remover um diretório que não está vazio.";
                        }
                        break;
                    }
                }
            }

            if (!encontrado) {
                result = "Arquivo ou diretório não encontrado.";
            }
        }

        return result;
    }

    public String chmod(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: chmod");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        String[] s = parameters.split(" ");
        boolean dir = false;
        boolean arq = false;
        int pos = 0;
        for (int i = 0; i < d.getDiretorio().size(); i++) {
            if (s[0].equals(d.getDiretorio().get(i).getNome())) {
                dir = true;
                pos = i;
                break;
            }
        }
        for (int i = 0; i < a.size(); i++) {
            if (s[0].equals(a.get(i).getNome()) || s[0].equals(a.get(i).getNome() + "." + a.get(i).getExtencao())) {
                arq = true;
                pos = i;
                break;
            }
        }
        if (dir) {
            d.getDiretorio().get(pos).setPermissao(Integer.parseInt(s[1]));
            result = "Permissao do diretorio alterada com sucesso";
            System.out.println(d.getDiretorio().get(pos).getPermissao());
        } else if (arq) {
            a.get(pos).setPermissao(Integer.parseInt(s[1]));
            result = "Permissao do arquivo alterada com sucesso";
            System.out.println(a.get(pos).getPermissao());
        } else {
            result = "Diretorio ou Arquivo nao encontrado";
        }

        //fim da implementacao do aluno
        return result;
    }

    @Override
    public String createfile(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: createfile");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f1 = true;
        String[] s = parameters.split(" ");
        String[] e = s[0].split("\\.");
        for (int i = 0; i < a.size(); i++) {
            if (e[0] == a.get(i).getNome()) {
                f1 = false;
                break;
            }
        }
        if (f1) {
            if (raiz) {
                result = "Nao eh possivel criar um arquivo a partir da Raiz";
            } else {
                String[] f = parameters.split(s[1]);
                String c = s[1];
                try {
                    c = s[1] + f[1];
                } catch (Exception ex) {
                }
                a.add(new Arquivo(d.getDiretorio().get(atual).getNome(), c, e[0], e[1], 0));
                d.getDiretorio().get(atual).criarArquivo(a.get(indexa));
                result = "Arquivo criado com sucesso";
            }
        } else {
            result = "Nome de Arquivo ja existente";
        }
        //fim da implementacao do aluno
        return result;
    }

    public String cat(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: cat");
        System.out.println("\tParametros: " + parameters);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getNome().equals(parameters)) {
                result = a.get(i).getConteudo();
                break;
            }
        }
        System.out.println(result);
        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String batch(String parameters) {
        //variavel result deverá conter o que será impresso na tela após o comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: batch");
        System.out.println("\tParametros: " + parameters);

        // Verifique se o parâmetro não está vazio
        if (parameters.isEmpty()) {
            result = "Parâmetro inválido. Forneça o nome do arquivo de saída.";
        } else {
            // Verifique se o parâmetro termina com ".txt" e, se não, adicione a extensão
            if (!parameters.endsWith(".txt")) {
                parameters += ".txt";
            }

            try {
                FileWriter writer = new FileWriter(parameters);
                // Escreva informações sobre os diretórios
                writer.write("Diretórios:\n");
                for (Diretorio dir : d.getDiretorio()) {
                    writer.write(dir.getNome() + "\n");
                }

                // Escreva informações sobre os arquivos
                writer.write("\nArquivos:\n");
                for (Arquivo arquivo : a) {
                    writer.write(arquivo.getNome() + "." + arquivo.getExtencao() + "\n");
                }

                writer.close();
                result = "Estrutura do sistema de arquivos exportada para " + parameters;
            } catch (IOException e) {
                result = "Erro ao escrever no arquivo " + parameters;
            }
        }

        return result;
    }

    /*
    public String batch(String parameters) {
        // Variável result deverá conter o que será impresso na tela após o comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: batch");
        System.out.println("\tParametros: " + parameters);

        // Verifique se o parâmetro não está vazio
        if (parameters.isEmpty()) {
            result = "Parâmetro inválido. Forneça o nome do arquivo de saída.";
        } else {
            // Verifique se o parâmetro termina com ".txt" e, se não, adicione a extensão
            if (!parameters.endsWith(".txt")) {
                parameters += ".txt";
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader(parameters));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    String comando = parts[0];

                    if (comando.equals("ls")) {
                        result += ls(parameters) + "\n";
                    } else if (comando.equals("mkdir")) {
                        result += mkdir(parameters) + "\n";
                    } else if (comando.equals("cd")) {
                        result += cd(parameters) + "\n";
                    } else if (comando.equals("rmdir")) {
                        result += rmdir(parameters) + "\n";
                    } else if (comando.equals("cp")) {
                        result += cp(parameters) + "\n";
                    } else if (comando.equals("mv")) {
                        result += mv(parameters) + "\n";
                    } else if (comando.equals("rm")) {
                        result += rm(parameters) + "\n";
                    } else if (comando.equals("chmod")) {
                        result += chmod(parameters) + "\n";
                    } else if (comando.equals("createfile")) {
                        result += createfile(parameters) + "\n";
                    } else if (comando.equals("cat")) {
                        result += cat(parameters) + "\n";
                    } else if (comando.equals("batch")) {
                        result += batch(parameters) + "\n";
                    } else if (comando.equals("dump")) {
                        result += dump(parameters) + "\n";
                    } else {
                        result += "Comando desconhecido: " + comando + "\n";
                    }
                }

                reader.close();
                result += "Comandos Executados.\n" + "Estrutura do sistema de arquivos exportada para " + parameters;
            } catch (IOException e) {
                result = "Erro ao ler o arquivo " + parameters;
            }
        }

        return result;
    }*/

    public String dump(String parameters) {
        //variavel result deverá conter o que será impresso na tela após o comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: dump");
        System.out.println("\tParametros: " + parameters);

        // Verifique se o parâmetro não está vazio
        if (parameters.isEmpty()) {
            result = "Parâmetro inválido. Forneça o nome do arquivo de saída.";
        } else {
            // Verifique se o parâmetro termina com ".txt" e, se não, adicione a extensão
            if (!parameters.endsWith(".txt")) {
                parameters += ".txt";
            }

            try {
                FileWriter writer = new FileWriter(parameters);
                // Escreva informações sobre os diretórios
                writer.write("Diretórios:\n");
                for (Diretorio dir : d.getDiretorio()) {
                    writer.write(dir.getNome() + "\n");
                }

                // Escreva informações sobre os arquivos
                writer.write("\nArquivos:\n");
                for (Arquivo arquivo : a) {
                    writer.write(arquivo.getNome() + "." + arquivo.getExtencao() + "\n");
                }

                writer.close();
                result = "Estrutura do sistema de arquivos exportada para " + parameters;
            } catch (IOException e) {
                result = "Erro ao escrever no arquivo " + parameters;
            }
        }

        return result;
    }

    public String info() {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: info");
        System.out.println("\tParametros: sem parametros");

        //nome do aluno
        String name = "Pedro Henrique Galhardi";
        //numero de matricula
        String registration = "202011020012";
        //versao do sistema de arquivos
        String version = "3.0";

        result += "Nome do Aluno:        " + name;
        result += "\nMatricula do Aluno:   " + registration;
        result += "\nVersao do Kernel:     " + version;

        return result;
    }

}
