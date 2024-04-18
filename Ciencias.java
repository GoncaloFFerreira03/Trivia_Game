import java.util.ArrayList;

/**
 * Subclasse da {@link Pergunta} que representa as perguntas sobre Ciencias
 */
class Ciencias extends Pergunta {
    /**
     *Pontuacao extra para uma pergunta de Ciências
     */
    private final int pontuacaoExtra = 5;
    public Ciencias(String questao, String correta,ArrayList<String> opcoes, String categoria, int nivel,int  pontuacao) {
        super(questao, correta, opcoes, categoria, nivel,pontuacao);
        this.setOpcoes(variaOpcoes(opcoes));
    }
    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Ciencias
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {
        return getPontuacaoinicial() + 5;
    }
    /**
     * Varia a dificuldade das opçoes consoante o  avançar do jogo /aumento da dificuldade
     * <p>
     *     Troca a partir do meio do jogo, de faceis para dificeis
     * @param opcoes ArrayList de todas as opçoes (faceis e dificeis)
     * @return ArrayList com opçoes faceis ou opçoes dificeis
     */
    public ArrayList<String> variaOpcoes(ArrayList<String> opcoes)
    {   //Se a pergunta aparecer antes do meio retira as ultimas opçoes/ as dificeis
        if(getNivel()<POOTrivia.numPerguntas/2 +1 )
        {
            for(int i=0;i<4;i++) {
                opcoes.remove(5);
            }
        }
        //caso contrario  remove as primeiras/fáceis
        else
        {
            for(int i=0;i<4;i++) {
                opcoes.remove(1);
            }
        }
        return opcoes;
    }
}
