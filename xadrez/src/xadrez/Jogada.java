package xadrez;
import java.util.ArrayList;

public class Jogada {
    
    private Tabuleiro tabuleiro; //jogada conhece tabuleiro, o jogador que ta fznd a jogada
    private Jogador jogador; //e constroi o caminho
    private Caminho caminho;
    private int linhaO;
    private int linhaD;
    private char colunaO;
    private char colunaD;
    
    public Jogada(int linhaO, char colunaO, int linhaD, char colunaD, Jogador jog, Tabuleiro tab) {
        tabuleiro =  tab;
        jogador = jog;
        this.linhaO = linhaO;
        this.linhaD = linhaD;
        this.colunaO = colunaO;
        this.colunaD = colunaD;
    }
    
    public boolean ehValida() {
        //se uma das casas esta fora dos limites do tabuleiro, invalido
        if(!tabuleiro.noLimite(linhaO, colunaO) || !tabuleiro.noLimite(linhaD, colunaD)) return false;
        
        //se a casa inicial nao estiver ocupada, que peça o abençoado vai mexer
        if(!caminho.getCasaInicial().estaOcupada()) return false; 
        
        //se a peça da casa incial nao for do jogador aqui
        if(!jogador.ehDoJogador(caminho.getCasaInicial().getPeca())) return false;
        
        //se a peça na última casa for do próprio jogador (tu quer se capturar eh)
        if(jogador.ehDoJogador(caminho.getCasaFinal().getPeca())) return false;
        
        //se a peça em questão for um peão, e a casa que ele quer ir esta ocupada, temos que fzr uma verificação diferente
        if(caminho.getCasaInicial().getPeca().tipo().equals("Peao") && caminho.getCasaFinal().estaOcupada()) {
            if(peaoAtaque(linhaO, colunaO, linhaD, colunaD) && !jogador.ehDoJogador(caminho.getCasaFinal().getPeca())) return true;
            return false; 
        }
        
        //por fim, se a peça nao pode fzr esse movimento, inválido
        if(!caminho.getCasaInicial().getPeca().movimentoValido(linhaO, colunaO, linhaD, colunaD)) return false;
        
        criarCaminho(); //se estiver tudo ok, criamos caminho
        //se o caminho nao esta livre e a peça em questão não é o cavalo, então esse movimento eh invalido
        if(!caminho.getCasaInicial().getPeca().tipo().equals("Cavalo") && !caminho.estaLivre()) return false;
        
        
        return true; //se passar por tudo isso, eh valido
    }
    
    public boolean ehXeque(Jogador oponente) { //fiquei mt confusa, mas se quem acabou de fzr uma jogada foi o nosso querido aqui
        //temos que ver se deu xeque no rei do outro cara, certo?
        
        Casa casaRei = tabuleiro.acharRei(oponente.getCor()); //achando o rei do oponente

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) { //vamos passar por todo o tabuleiro
                Casa casa = tabuleiro.getCasa(i, j);
                if (casa.estaOcupada() && jogador.ehDoJogador(casa.getPeca())) { //quando achamos uma peça do nosso jogador
                    if (casa.getPeca().movimentoValido(i, j, casaRei.getLinha(), casaRei.getColuna()) && !casa.getPeca().tipo().equals("Peao")) {
                        return true; //verificamos se o movimento que a levaria ate o rei é válido, exceto que no caso do peao, nao podemos fzr uma verificação comum
                    }
                    
                    if(casa.getPeca().tipo().equals("Peao") && peaoAtaque(i, j, casaRei.getLinha(), casaRei.getColuna())) {
                        return true; //se a peça for um peao, temos que analisar a posição de atque dele
                    }
                }
            }
        }
    
        return false;
    }

    
    public boolean ehXequeMate(Jogador oponente) { //pra xeque mate dai vamo ter que checar se todas as casas possiveis para onde o rei pode ir estao em xeque T-T
        
        Casa casaRei = tabuleiro.acharRei(oponente.getCor());
        int linhaRei = casaRei.getLinha();
        char colunaRei = casaRei.getColuna();
        
        ArrayList<Jogada> testeRei = new ArrayList<>();
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei + 1, colunaRei, oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei, (char)(colunaRei + 1), oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei - 1, colunaRei, oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei, (char)(colunaRei - 1), oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei + 1, (char)(colunaRei + 1), oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei - 1, (char)(colunaRei - 1), oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei + 1, (char)(colunaRei - 1), oponente, tabuleiro));
        testeRei.add(new Jogada(linhaRei, colunaRei, linhaRei - 1, (char)(colunaRei + 1), oponente, tabuleiro));
        //criando as 8 jogadas possiveis do rei oponente
        
        for(Jogada j: testeRei) {
            if(j.ehValida() && !j.ehXeque(oponente)) {
                return false;
            } //se tiver alguma jogada valida para o rei que nao for xeque, ent n é xeque mate
        }
        
        return true; //se ele terminar o for com as 8 jogadas sem achar uma valida sem perigo para o rei, ent é xeque mate
        
    }
    
    private void criarCaminho() {
        caminho = new Caminho(tabuleiro.getCasa(linhaO, colunaO), tabuleiro.getCasa(linhaD, colunaD)); //colocando casa inicial e final
            
        String percurso = tabuleiro.getCasa(linhaO, colunaO).getPeca().caminho(linhaO, colunaO, linhaD, colunaD); //vms usar o método de caminho que existe em peça
            
        if(percurso.length() > 4) { //se o tam de percurso for 4, ent só tem duas casas (final e inicial) nao há caminho pra fzr
            for(int i = 2; i < percurso.length() - 2; i += 2) {
                char linha = percurso.charAt(i);
                char coluna = percurso.charAt(i+1);
                caminho.adicionarCasa(tabuleiro.getCasa((int)(linha - 97 + 1), coluna));
            }
        }
    }
    
    private boolean peaoAtaque(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(linhaD - linhaO == 1 && Math.abs(colunaD - colunaO) == 1) return true; //ele precisa estar indo na diagonal pra frente, n importa dir ou esq
        return false;
    }
}

//meu jesus nem sei mais o que ta acontecendo nesse codigo
