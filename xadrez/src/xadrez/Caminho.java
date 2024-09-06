/*
O caminho:
- tem uma ou mais casas 
- conhece o tabuleiro -> não consigo alterar para o caminho utilizar o tabuleiro
*/

package xadrez;

import java.util.ArrayList;
import java.util.List;

public class Caminho {
    private List<Casa> casas; // Lista de casas que formam o caminho
    private Tabuleiro casaInicial;
    private Tabuleiro casaFinal;
            
    public Caminho(Casa inicio, Casa fim) {
        this.casas = new ArrayList<>();
        /*
            Na classe tabuleiro é responsável por criar as casas
            A posição inicial e final são posições no tabuleiro 
            O caminho são as pessas entre essas duas posições
            Essas peças são adicionadas na lista casas.
            
            Não sei a lógica para receber duas posições na forma de tabuleiro
        */
    }
    
    // Adiciona uma casa ao caminho
    public void adicionarCasa(Casa casa) {
        this.casas.add(casa);
    }
    
    // Verifica se todas as casas no caminho estão livres (exceto a inicial e a final)
    public boolean estaLivre() {
        if (casas.size() <= 2) return true; // Se houver 0 ou 1 casas no caminho, está livre por definição
        
        for (int i = 1; i < casas.size() - 1; i++) { // Ignora a primeira e a última casa
            if (casas.get(i).isOcupada()) {
                return false; // Se qualquer casa intermediária estiver ocupada, o caminho não está livre
            }
        }
        return true; // Se todas as casas intermediárias estiverem livres, o caminho está livre
    }
    
    // Retorna a casa inicial do caminho
    public Casa casaInicial() {
        if (!casas.isEmpty()) {
            return casas.get(0); // Retorna a primeira casa na lista
        }
        return null; // Se a lista estiver vazia, retorna null
    }
    
    // Retorna a casa final do caminho
    public Casa casaFinal() {
        if (!casas.isEmpty()) {
            return casas.get(casas.size() - 1); // Retorna a última casa na lista
        }
        return null; // Se a lista estiver vazia, retorna null
    }
    
    // Método auxiliar para retornar a sequência de casas
    public List<Casa> getCasas() {
        return casas;
    }
    
    // Método para limpar o caminho
    public void limparCaminho() {
        casas.clear();
    }
}
