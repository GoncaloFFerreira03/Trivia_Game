import java.util.ArrayList;
/**
 * Subclasse da classe {@link Desporto} que representa as perguntas de Desporto sobre Natacao
 */
public class Natacao extends Desporto {
    /**
     *Pontuacao extra para uma pergunta de Natacao
     */
    private final int pontuacaoExtra = 3;
    public Natacao(String questao,String correta, ArrayList<String> opcoes, String categoria, int nivel,int pontuacao) {
        super(questao, correta, opcoes, categoria, nivel,pontuacao);
    }
    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Natacao
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {
        return super.pontuacao() + pontuacaoExtra;
    }
}
