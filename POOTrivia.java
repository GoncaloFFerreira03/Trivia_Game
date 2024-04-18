import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class POOTrivia {
    /**
     * Objeto do tipo ficheiro que guarda os dados das perguntas
     */
    private Ficheiro f;
    /**
     * Numero de Perguntas a ser mostradas em cada jogo
     */
    static final int numPerguntas = 5;
    /**
     * {@link ArrayList} de {@link Pergunta}  com as Perguntas mostradas durante o jogo atual
     */
    ArrayList <Pergunta> perguntasFinal = new ArrayList<>();

    /**
     * Escolhe um número predefenido de perguntas aleatórias a partir das linhas lidas
     */
    public void escolherPergunta() {
        f.perguntas = new ArrayList<String>();
        Random rand = new Random();
        for (int i = 0; i < POOTrivia.numPerguntas && !f.linhas.isEmpty(); i++) {
            int sortIdx = rand.nextInt(f.linhas.size());
            String linha = f.linhas.get(sortIdx);
            f.perguntas.add(linha);
            f.linhas.remove(sortIdx);
        }
        if (f.perguntas.size() != POOTrivia.numPerguntas) {
            System.out.println("ERRO: Número de Perguntas escolhidas: " + f.perguntas.size() + ", diferente das " + POOTrivia.numPerguntas + " pretendidas.");
        }
        else System.out.println(POOTrivia.numPerguntas + " perguntas escolhidas aleatoriamente");
    }
    /**
     * Transforma as Strings de perguntas em objetos {@link Pergunta}, do tipo respetivo
     */
    public void perguntas() {
        ArrayList<String> perguntas = f.perguntas;
        int nivel =1;
        for (String pergunta : perguntas) {
            //divide a string pergunta nos seus componentes divididos por |
            String[] partes = pergunta.split("\\|");
            ArrayList<String> opcoes = new ArrayList<>(Arrays.asList(partes).subList(2, partes.length-1));
            //pontuacao da base da pergunta
            int pontuacao = Integer.parseInt(partes[partes.length-1]);
            //partes[0] contem o tipo de Perguntas
            //partes[1] questao
            //partes[2] resposta certa
            switch (partes[0]) {
                case ("Ski"):
                    perguntasFinal.add(new Ski(partes[1], partes[2], opcoes, partes[0], nivel,pontuacao));
                    break;
                case ("Natação"):
                    perguntasFinal.add(new Natacao(partes[1], partes[2], opcoes, partes[0], nivel,pontuacao));
                    break;
                case ("Futebol"):
                    perguntasFinal.add(new Futebol(partes[1], partes[2], opcoes, partes[0], nivel,pontuacao));
                    break;
                case ("Ciência"):
                    perguntasFinal.add(new Ciencias(partes[1], partes[2], opcoes, partes[0], nivel,pontuacao));
                    break;
                case ("Arte"):
                    perguntasFinal.add(new Arte(partes[1], partes[2], opcoes, partes[0], nivel,pontuacao));
                    break;
            }
            nivel += 1;
        }
    }

    /**
     * Inicializa o jogo.
     * <p>
     *  <ul>
     *     <li>Cria uma instância da classe POOTrivia.</li>
     *     <li>Lê perguntas de um ficheiro.</li>
     *     <li>Inicializa uma interface gráfica (GUI) para o jogo usando a classe Gui.</li>
     * </ul>
     * <p>
     * Nota: O número de perguntas a serem escolhidas é definido pela variável numPerguntas.
     * </p>
     */
    public static void iniciarJogo()
    {
        POOTrivia trivia = new POOTrivia();
        trivia.f=new Ficheiro("pootrivia.txt");
        Gui gui = new Gui(trivia);
    }
    public static void main(String[] args) {
        iniciarJogo();
    }
}
