import java.util.ArrayList;
/**
 * Subclasse da classe {@link Desporto} que representa as perguntas de Desporto sobre futebol
 */
public class Futebol extends Desporto {
    /**
     *Pontuacao extra para uma pergunta de Desporto
     */
    private final int pontuacaoExtra = 3;
    public Futebol(String questao, String correta, ArrayList<String> opcoes, String categoria,int nivel,int pontuacao) {
        super(questao, correta,opcoes,categoria,nivel,pontuacao);
        this.setOpcoes(variaOpcoes(opcoes));
    }
    /**
     * Método que permite o calculo da pontuacao de uma pergunta de Futebol
     * @return int com a pontuacao da pergunta
     */
    public int pontuacao() {
        return super.pontuacao() + pontuacaoExtra;
    }
    /**
     * Varia a dificuldade das opçoes consoante o  avançar do jogo /aumento da dificuldade
     * <p>
     *     Troca a partir do meio do jogo, de nomes de jogadores para numeros das camisolas
     * @param opcoes ArrayList de todas as opçoes (faceis e dificeis)
     * @return ArrayList com opçoes faceis(nomes) ou opçoes dificeis(numeros)
     */
    public ArrayList<String> variaOpcoes(ArrayList<String> opcoes)
    {   //se aparecer antes do meio remove as opçoes com numeros
        if(getNivel()<POOTrivia.numPerguntas/2 +1 )
        {
            for(int i=0;i<5;i++) {
                opcoes.remove(5);
            }
        }
        //senão remove as opçoes com nomes
        else
        {
            for(int i=0;i<5;i++) {
                opcoes.remove(0);
            }
            //troca a opçao correta do nome para o numero do jogador
            super.setCorreta(opcoes.get(0));
        }
        return opcoes;
    }
}
