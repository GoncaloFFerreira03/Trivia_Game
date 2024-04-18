import java.io.*;
import java.util.ArrayList;


/**
 * Classe Ficheiro que contém um construtor de Ficheiro através das perguntas de um arquivo de texto.
 */
class Ficheiro{
    /**
     * Lista que armazena as linhas lidas do arquivo
     */
    ArrayList<String> linhas = new ArrayList<>();

    /**
     * Lista que armazena as perguntas escolhidas aleatoriamente
     */
    ArrayList<String> perguntas = new ArrayList<>();

    /**
     * Cria um objeto ficheiro através das linhas de um arquivo texto
     * @param nomeFicheiro nome do arquivo de texto
     */
    public Ficheiro(String nomeFicheiro) {
        File f = new File(nomeFicheiro);
        if (f.exists() && f.isFile()){
            try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    linhas.add(linha);
                }
            }catch (FileNotFoundException ex){
                System.out.println("Erro a abrir ficheiro de texto");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro na leitura do ficheiro: ");
            }

        }else {
            System.out.println("Ficheiro de perguntas não disponível");
        }
    }
}
