import java.util.ArrayList;

/**
 * Subclasse da {@link Pergunta} que representa as perguntas sobre Arte
 */
class Arte extends Pergunta {
    /**
     *Fator a multiplicar para obter a pontuacao extra de uma pergunta de Arte
     */
    private final int pontuacaoExtra = 10;
    public Arte(String questao, String correta, ArrayList<String> opcoes, String categoria,int nivel,int pontuacao) {
        super(questao, correta, opcoes, categoria,nivel,pontuacao);
        this.setOpcoes(variaOpcoes(opcoes));
    }

    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Artes
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {
        return getPontuacaoinicial() * pontuacaoExtra;
    }

    /**
     * Varia o número de opções de resposta consoante o  avançar do jogo /aumento da dificuldade
     * <p>
     *     Modifica a partir do meio do jogo
     * @param opcoes ArrayList de opçoes inicias
     * @return ArrayList com as opçoes variadas
     */
    public ArrayList<String> variaOpcoes(ArrayList<String> opcoes) {
        //retira opçoes de resposta se as perguntas ocorrerem antes de metade do jogo
        if(getNivel()<POOTrivia.numPerguntas/2 +1 )
        {   //remove as duas ultimas opçoes de resposta
            opcoes.remove(3);
            opcoes.remove(3);
        }
        return opcoes;
    }
}
