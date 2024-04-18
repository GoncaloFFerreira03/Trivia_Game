import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Classe que representa a interface grafica do jogo PooTrivia
 *
 */
public class Gui extends JFrame {
    private JLabel titulo;
    private final JPanel panel;
    private JButton iniciar,top3,sair,opcao1,opcao2,opcao3,opcao4,opcao5, verdadeiro, falso, enviar, menu;
    private JTextField inserir;
    private POOTrivia pooTrivia;
    /**
     * Lista que contem os 3 jogos com mais ranking
     * <p>
     *     ordenados por ordem decrescente de  rank
     */
    private List<Jogo> listaTop3;
    private ArrayList<Pergunta> perguntasAcertadas = new ArrayList<>();
    private ArrayList<Pergunta> perguntasErradas = new ArrayList<>();

    /**
     * objeto do tipo {@link Jogo} que guarda os dados de um jogo finalizado
     */
    private Jogo jogoFinalizado;

    /**
     * Construtor da classe Gui
     * Inicializa a interface gráfica com os componentes necessários
     * @param pooTrivia Instância da classe POOTrivia
     */
    public Gui(POOTrivia pooTrivia) {
        super();
        this.setPreferredSize(new Dimension(900, 600));
        this.setTitle("POOTRIVIA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pooTrivia = pooTrivia;

        panel= new JPanel();
        iniciar = new JButton("Iniciar");
        top3 =new JButton("Top3");
        sair = new JButton("Sair");
        enviar = new JButton();
        inserir = new JTextField();
        menu = new JButton();

        opcao1 = new JButton();
        opcao2 = new JButton();
        opcao3 = new JButton();
        opcao4 = new JButton();
        opcao5 = new JButton();

        verdadeiro = new JButton("Verdadeiro");
        falso = new JButton("Falso");

        ButtonListener buttonActionListener = new ButtonListener(pooTrivia);

        iniciar.addActionListener(buttonActionListener);
        top3.addActionListener(buttonActionListener);
        sair.addActionListener(buttonActionListener);
        enviar.addActionListener(buttonActionListener);
        opcao1.addActionListener(buttonActionListener);
        opcao2.addActionListener(buttonActionListener);
        opcao3.addActionListener(buttonActionListener);
        opcao4.addActionListener(buttonActionListener);
        opcao5.addActionListener(buttonActionListener);
        verdadeiro.addActionListener(buttonActionListener);
        falso.addActionListener(buttonActionListener);
        menu.addActionListener(buttonActionListener);

        painelInicial();
        this.add(panel);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    /**
     * Método responsável por exibir o painel inicial da interface gráfica.
     */
    public void painelInicial(){
        titulo = new JLabel("Bem Vindo ao POOTRIVIA!", SwingConstants.CENTER);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        titulo.setFont(new Font("Verdana", Font.BOLD, 30));
        titulo.setBounds(200, 50, 500, titulo.getPreferredSize().height);

        iniciar.setFont(new Font("Arial", Font.PLAIN, 16));
        iniciar.setBounds(350, 150, 200, 75);

        top3.setFont(new Font("Arial", Font.PLAIN, 16));
        top3.setBounds(350, 250, 200, 75);

        sair.setFont(new Font("Arial", Font.PLAIN, 16));
        sair.setBounds(350, 350, 200, 75);

        panel.add(titulo);
        panel.add(iniciar);
        panel.add(top3);
        panel.add(sair);
    }

    /**
     * Método responsável por exibir o painel de ranking com a pontuação obtida no jogo
     * @param pontuacao Pontuação do jogador.
     */
    public void painelRanking(int pontuacao)
    {
        panel.removeAll();
        panel.repaint();

        JLabel ranking = new JLabel("Pontuacao: "+pontuacao);
        ranking.setFont(new Font("Verdana", Font.BOLD, 30));
        ranking.setForeground(Color.BLACK);
        ranking.setBounds(300, 200, 400, ranking.getPreferredSize().height);

        JTextArea nome = new JTextArea("Introduza o seu nome");
        nome.setFont(new Font("Verdana", Font.BOLD, 30));
        nome.setForeground(Color.BLACK);
        nome.setBounds(250, 250, 400, nome.getPreferredSize().height);
        nome.setEditable(false);


        inserir.setBounds(250, 300, 400, inserir.getPreferredSize().height);
        inserir.setEditable(true);

        enviar.setFont(new Font("Verdana", Font.BOLD, 30));
        enviar.setBounds(675, 475, 200, 75);
        enviar.setText("Enviar");

        panel.add(ranking);
        panel.add(nome);
        panel.add(inserir);
        panel.add(enviar);

    }
    /**
     * Método responsável por exibir o painel do Top 3
     */
    public void painelTop3(){
        panel.removeAll();
        panel.repaint();

        JLabel ttTop3 = new JLabel("Top3");
        ttTop3.setFont(new Font("Verdana", Font.BOLD, 30));
        ttTop3.setForeground(Color.BLACK);
        ttTop3.setBounds(400, 50, 300, ttTop3.getPreferredSize().height);

        for(int i = 0; i<listaTop3.size(); i++){
            Jogo j = listaTop3.get(i);
            JLabel rank = new JLabel(j.getNomeJogador() + "-> " + j.calculaRanking());
            rank.setFont(new Font("Arial", Font.BOLD, 20));
            rank.setForeground(Color.BLACK);
            rank.setBounds(375, 150 + i*75, 300, ttTop3.getPreferredSize().height);
            panel.add(rank);
        }

        menu.setFont(new Font("Verdana", Font.BOLD, 30));
        menu.setBounds(675, 475, 200, 75);
        menu.setText("Menu");


        panel.add(ttTop3);
        panel.add(menu);

    }

    /**
     * Método responsável por exibir o painel com as perguntas e as respetivas opções.
     * @param categoria tipo de pergunta.
     * @param pergunta  Enunciado da pergunta.
     * @param opcoes    Lista com as opções de resposta.
     */
    public void painelPerguntasOpcoes(String categoria, String pergunta, ArrayList<String> opcoes) {
        panel.repaint();
        panel.removeAll();

        JLabel catego = new JLabel(categoria);
        catego.setFont(new Font("Verdana", Font.BOLD, 30));
        catego.setForeground(Color.BLACK);

        JTextArea perg = new JTextArea(pergunta, 3, 30);
        perg.setFont(new Font("Arial", Font.BOLD, 20));
        perg.setForeground(Color.BLACK);
        perg.setWrapStyleWord(true);
        perg.setLineWrap(true);
        perg.setEditable(false);

        catego.setBounds(400, 25, 400, catego.getPreferredSize().height);
        perg.setBounds(250, 75, perg.getPreferredSize().width, perg.getPreferredSize().height);

        panel.add(catego);
        panel.add(perg);

        int opcxx = 300;
        int opcyy = 250;

        opcao1.setBounds(opcxx, opcyy - 88, 300, 50);
        opcao1.setFont(new Font("Arial", Font.PLAIN, 20));

        opcao2.setBounds(opcxx, opcyy - 12, 300, 50);
        opcao2.setFont(new Font("Arial", Font.PLAIN, 20));

        opcao3.setBounds(opcxx, opcyy + 62, 300, 50);
        opcao3.setFont(new Font("Arial", Font.PLAIN, 20));

        opcao4.setBounds(opcxx, opcyy + 138, 300, 50);
        opcao4.setFont(new Font("Arial", Font.PLAIN, 20));

        opcao5.setBounds(opcxx, opcyy + 213, 300, 50);
        opcao5.setFont(new Font("Arial", Font.PLAIN, 20));


        verdadeiro.setBounds(opcxx, opcyy - 12, 300, 50);
        verdadeiro.setFont(new Font("Arial", Font.PLAIN, 20));

        falso.setBounds(opcxx, opcyy + 62, 300, 50);
        falso.setFont(new Font("Arial", Font.PLAIN, 20));

        if(opcoes.size()==2)
        {
            panel.add(verdadeiro);
            panel.add(falso);
        }
        else if(opcoes.size()==3)
        {

            opcao1.setText(opcoes.get(0));
            opcao2.setText(opcoes.get(1));
            opcao3.setText(opcoes.get(2));

            panel.add(opcao1);
            panel.add(opcao2);
            panel.add(opcao3);
        }
        else
        {

            opcao1.setText(opcoes.get(0));
            opcao2.setText(opcoes.get(1));
            opcao3.setText(opcoes.get(2));
            opcao4.setText(opcoes.get(3));
            opcao5.setText(opcoes.get(4));

            panel.add(opcao1);
            panel.add(opcao2);
            panel.add(opcao3);
            panel.add(opcao4);
            panel.add(opcao5);
        }
    }

    /**
     * Classe interna que implementa o ActionListener para lidar com eventos dos botões
     */
    private class ButtonListener implements ActionListener {
        private ArrayList <Pergunta> perguntasFinal;
        private int pontuacaoPerg;
        private int index;

        public ButtonListener(POOTrivia trivia) {
            this.perguntasFinal = trivia.perguntasFinal;
        }

        //Funçoes para cada botao da Gui
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==iniciar) {
                perguntasAcertadas.clear();
                perguntasErradas.clear();
                perguntasFinal.clear();
                recomecarJogo(pooTrivia);
                index = 0;
                nextQuestion(perguntasFinal.get(index).getCategoria(), perguntasFinal.get(index).getQuestao(), perguntasFinal.get(index).getOpcoes());
            }
            else if(e.getSource()==enviar)
            {
                String nomeJogador = inserir.getText().trim();  // Remove espaços em branco no início e no final
                //verifica se o nome introduzido é válido, não está vazio e náo contém números
                if (nomeJogador.isEmpty() || nomeJogador.isBlank() || nomeJogador.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }else {
                    guardaFicheiro(nomeJogador);   //guarda o jogo num ficheiro
                    calculaTop3();   //calcula os jogos com melhor pontuaçao
                    imprimeTop3();     //imprime o top3 na consola
                    painelTop3();   //avanca para o painel do Top3
                }
            }

            else if(e.getSource()==top3){
                if (JOptionPane.showConfirmDialog(null, "Queres aceder ao top 3?", "top3", JOptionPane.YES_NO_OPTION) == 0) {
                    calculaTop3();
                    imprimeTop3();
                    painelTop3();
                }
            }
            else if(e.getSource()==sair){
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
            else if (e.getSource() == menu) {
                panel.repaint();
                panel.removeAll();
                painelInicial();

                //se acertou
            } else if(e.getActionCommand().equals(perguntasFinal.get(index).getCorreta()))
            {
                pontuacaoPerg=perguntasFinal.get(index).pontuacao();  //calcula a pontuaçáo obtida por acertar na pergunta
                JOptionPane.showMessageDialog(null,"Acertou\n+"+pontuacaoPerg);

                perguntasAcertadas.add(perguntasFinal.get(index));
                if(index<POOTrivia.numPerguntas-1) {
                    index++;
                    //troca de pergunta
                    nextQuestion(perguntasFinal.get(index).getCategoria(), perguntasFinal.get(index).getQuestao(), perguntasFinal.get(index).getOpcoes());
                }
                else
                {   //se for a ultima pergunta mostra a pontuacao
                    painelRanking(calculaPontuacao());
                }
            }
            //Se errou
            else if(!e.getActionCommand().equals(perguntasFinal.get(index).getCorreta()))
            {
                JOptionPane.showMessageDialog(null,"Errou");
                perguntasErradas.add(perguntasFinal.get(index));
                if(index<POOTrivia.numPerguntas-1) {
                    index++;
                    //troca de pergunta
                    nextQuestion(perguntasFinal.get(index).getCategoria(), perguntasFinal.get(index).getQuestao(), perguntasFinal.get(index).getOpcoes());
                }
                else
                {   //se for a ultima pergunta mostra a pontuacao
                    painelRanking(calculaPontuacao());
                }
            }
        }

        /**
         * Método para avançar para a próxima pergunta
         *
         * @param categoria Categoria da próxima pergunta.
         * @param pergunta  Texto da próxima pergunta.
         * @param opcoes    Lista de opções de resposta para a próxima pergunta.
         */
        public void nextQuestion(String categoria,String pergunta,ArrayList<String>opcoes)
        {
            Collections.shuffle(opcoes); //baralha as opçoes
            painelPerguntasOpcoes(categoria,pergunta,opcoes);
        }
    }

    /**
     * Método para calcular e retornar o Top 3 dos jogadores
     * @return Lista com ordem decrescente de pontuacao, com os três melhores jogos.
     */
    private List<Jogo> calculaTop3() {
        LinkedList<Jogo> jogosOrdenados = ordenaJogos();  //ordena os jogos guardados que estao nos ficheiros
        //Permite fazer o rank quando há menos de 3 jogos
        if (jogosOrdenados.size() < 3)  listaTop3 = jogosOrdenados.subList(0,jogosOrdenados.size());
        //se tivermos mais de 3 jogos , escolhemos os 3 primeiros da lista ordenada
        else listaTop3 = jogosOrdenados.subList(0,3);
        return listaTop3;
    }
    /**
     * Método para salvar o jogo num ficheiro
     * @param nomeJogador Nome do jogador
     */
    public void guardaFicheiro(String nomeJogador){
        jogoFinalizado = new Jogo(nomeJogador, perguntasAcertadas, perguntasErradas);
        jogoFinalizado.guardaFicheiroJogo();

    }

    /**
     * Método que ordena jogos por pontuação
     * @return uma lista de jogos ordenados de forma decrescente de pontuação
     */
    private LinkedList<Jogo> ordenaJogos() {
        String[] ficheirosNome = Jogo.encontraFicheirosJogo();
        Jogo jogo;
        LinkedList<Jogo> jogos = new LinkedList<>();
        for (String f : ficheirosNome) {
            jogo = Jogo.abreFicheiroJogo(f);
            if (jogo == null) continue;
            if (jogos.isEmpty()) jogos.add(jogo); //adiciona o 1º jogo
            else {
                int rank = jogo.calculaRanking();
                // Adiciona o jogo na posição correta na lista ordenada
                int index = 0;
                while (index < jogos.size() && rank <= jogos.get(index).calculaRanking()) {
                    index++;
                }
                jogos.add(index, jogo);

            }
        }
        return jogos;
    }

    /**
     * Método que imprime na consola o Top3
     */
    private void imprimeTop3(){
        System.out.println("Top3: ");
        int count = 0 ;
        for(Jogo j: listaTop3 ){
            System.out.printf("%d: ", count);
            System.out.println(j.toString() + j.calculaRanking());
            count++;
        }
    }

    /**
     * Método que permite calcular a pontuação de um jogo
     * @return pontuação obtida num jogo
     */
    private int calculaPontuacao(){
        int pontuacao = 0;
        for (Pergunta p : perguntasAcertadas){
            pontuacao += p.pontuacao();
        }
        return pontuacao;
    }

    /**
     * Método para reiniciar o jogo.
     * @param trivia Instância da classe POOTrivia
     */
    private void recomecarJogo(POOTrivia trivia)
    {
        trivia.escolherPergunta();
        trivia.perguntas();
    }
}

