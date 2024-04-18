import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Classe representa uma jogo no POOTrivia
 */
public class Jogo implements Serializable {
    /**
     * String que representa  a data e hora em que o ficheiro jogo foi criado
     */
    private String dataHora;
    /**
     * String contendo o nome do jogador
     */
    private String nomeJogador;
    /**
     * {@link ArrayList} de {@link Pergunta} contendo as perguntas que o jogador respondeu corretamente
     */

        private ArrayList<Pergunta> perguntasAcertadas;

    /**
     * {@link ArrayList} de {@link Pergunta} contendo as perguntas que o jogador respondeu erradamente
     */
        private ArrayList<Pergunta> perguntasErradas;
    /**
     * Objeto LocalDateTime que armazena a data e hora em que o jogo foi criado.
     */
        private LocalDateTime data;
    /**
     * Construtor da classe Jogo, que vai ser usada para guardar os dados do jogo num ficheiro
     * @param nomeJogador Nome do jogador
     * @param perguntasAcertadas Lista de perguntas que o jogador acertou
     * @param perguntasErradas Lista de perguntas que o jogador errou
     */
    public Jogo(String nomeJogador, ArrayList<Pergunta> perguntasAcertadas, ArrayList<Pergunta> perguntasErradas) {
        this.nomeJogador = nomeJogador;
        this.perguntasAcertadas = perguntasAcertadas;
        this.perguntasErradas = perguntasErradas;
        data = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataHora = data.format(formato);
    }

    /**
     * Retorna o nome do jogador
     * @retorn Uma string contendo o nome do jogador
     */
    public String getNomeJogador() {
        return nomeJogador;
    }

    /**
     * Retorna uma string contendo a data/hora do jogo e o nome do jogador
     *
     * @return Uma string representando o objeto Jogo
     */
    @Override
    public String toString() {
        return "Jogo{" +
                "dataHora='" + dataHora + '\'' +
                ", nomeJogador='" + nomeJogador + '\'';
    }

    /**
     * Classe que cria um ficheiro (ex: pootrivia_jogo_<data&hora>_<iniciais do nome do
     * jogador>.dat), onde guarda um objeto do tipo {@link Jogo}
     */
    void  guardaFicheiroJogo(){
        //cria uma string com data/hora no formato necessario para o nome do ficheiro
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dataFormatada = data.format(format);

        //obtem as inicias do Jogador
        String iniciasNome = "";
        String[] nomes = nomeJogador.split(" ");
        for(String n: nomes){
            iniciasNome += (n.charAt(0));
        }
        //converte as iniciais em maisculas
        iniciasNome = iniciasNome.toUpperCase(Locale.ROOT);

        //gera uma string com o nome do ficheiro.dat
        String nomeFicheiro = "pootrivia_jogo_" + dataFormatada + "_" + iniciasNome + ".dat";
        System.out.println(nomeFicheiro);

        //tenta salvar o objeto jogo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
                oos.writeObject(this);
                System.out.println("Objeto salvo com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o objeto: " + e.getMessage());
            }
    }

    /**
     * Abre um ficheiro contendo um objeto {@link Jogo} e devolve-o.
     *
     * @param NomeFicheiro Uma String contendo o nome do ficheiro com um objeto {@link Jogo}.
     * @return Um objeto do tipo {@link Jogo} lido a partir do ficheiro, ou {@code null} em caso de erro.
     */
    public static Jogo abreFicheiroJogo(String NomeFicheiro){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NomeFicheiro))) {
            Jogo j = (Jogo) ois.readObject();
            System.out.println(j);
            return j;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o objeto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Encontra na pasta do jogo , os ficheiros com o nome começado com "pootrivia_jogo_" e acabados com ".dat"
     * @return Array de Strings com os nome dos ficheiros encontrados , ou {@code null} em caso de erro
     */
    public static String[] encontraFicheirosJogo () {
        try{
            //obtem a dir da pasta do jogo
            String pasta = System.getProperty("user.dir");
            File f = new File(pasta);
            if (!f.exists() || !f.isDirectory()) {
                System.err.println("O diretório não existe ou não é um diretório válido.");
                return null;
            }
            //cria um filtro para os nomes de ficheiro comecados com "pootrivia_jogo_" e acabados com ".dat"
            FilenameFilter filter = (dir, name) -> name.startsWith("pootrivia_jogo_") && name.endsWith(".dat");
            //retorna String[] com os nome dos ficheiros começadas com "pootrivia_jogo_"e acabados com ".dat"
            return f.list(filter);

        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * calcula a pontuacao associada ao jogo
     * @return um int com a pontuacao desse jogo
     */
    public int calculaRanking(){
        int pontuacao = 0;
        for (Pergunta p : perguntasAcertadas){
           pontuacao += p.pontuacao();
        }
        return pontuacao;
    }


}

