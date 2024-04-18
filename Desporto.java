import java.util.ArrayList;

/**
 * Subclasse do método {@link Pergunta} que representa as perguntas sobre Desporto
 */
abstract class Desporto extends Pergunta {
    /**
     *Pontuacao extra para uma pergunta de Desporto
     */
    private final int pontuacaoExtra = 3;
    public Desporto(String questao, String correta, ArrayList<String> opcoes, String categoria,int nivel,int pontuacao) {
        super(questao, correta, opcoes,categoria,nivel,pontuacao);
    }
    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Desporto
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {
        return getPontuacaoinicial()+ pontuacaoExtra;
    }
}
