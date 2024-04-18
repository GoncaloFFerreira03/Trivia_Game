import java.util.ArrayList;
/**
 * Subclasse da classe {@link Desporto} que representa as perguntas de Desporto sobre Ski
 */
public class Ski extends Desporto{
    /**
     *Fator a multiplicar para obter a pontuacao extra de uma pergunta de Arte
     */
    private final int pontuacaoExtra = 2;
    public Ski(String questao, String correta, ArrayList<String> opcoes, String categoria,int nivel,int pontuacao) {
        super(questao, correta, opcoes, categoria,nivel,pontuacao);
    }
    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Ski
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {return super.pontuacao() * pontuacaoExtra;
    }
}