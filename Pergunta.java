import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe abstrata que representa uma Pergunta do jogo
 */
public abstract class Pergunta implements Serializable {
    /**
     * opçoes de resposta
     */
    private ArrayList<String> opcoes;
    /**
     * Pontuacao inicial(lida do ficheiro) associada à pergunta
     */
    private int pontuacaoinicial;
    /**
     * Enunciado da pergunta
     */
    private String questao;
    /**
     * resposta certa
     */
    private String correta;
    /**
     * tipo de pergunta
     */
    private String categoria;
    /**
     * permite gerir a dificuldade da pergunta
     */
    private int nivel;

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<String> opcoes) {
    this.opcoes = opcoes;
    }

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPontuacaoinicial() {
        return pontuacaoinicial;
    }

    public void setPontuacaoinicial(int pontuacaoinicial) {
        this.pontuacaoinicial = pontuacaoinicial;
    }

    /**
     * Cria um objeto Pergunta
     * @param questao enunciado
     * @param correta opçao correta
     * @param opcoes opçoes de resposta
     * @param categoria tipo de pergunta
     * @param nivel dificuldade da pergunta
     * @param pontuacaoinicial pontuacao base associada à pergunta
     */
    public Pergunta(String questao, String correta, ArrayList<String> opcoes, String categoria, int nivel,int pontuacaoinicial) {
        this.questao = questao;
        this.correta = correta;
        this.opcoes=opcoes;
        this.categoria=categoria;
        this.nivel=nivel;
        this.pontuacaoinicial=pontuacaoinicial;
    }

    /**
     * calcula a pontuacao da pergunta
     * @return pontuacao calculada
     */
    public abstract int pontuacao();

    /**
     * Retorna uma representação em string da pergunta.
     * @return Uma string que representa a pergunta.
     */
    @Override
    public String toString() {
        return "Pergunta{" +
                "questao='" + questao + '\'' +
                ", opcoes=" + opcoes + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}
