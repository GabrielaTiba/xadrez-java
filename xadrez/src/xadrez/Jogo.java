package projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {

    private Tabuleiro tabuleiro;
    private Jogador jogadores[] = new Jogador[2];
    private ArrayList<Jogada> jogada;
    private Peca pecas[] = new Peca[32];
    private int estado; // inicio 1, xeque 2, xeque-mate 3
    private int turnoJogador; // qual jogador é a vez branco(1) ou preto(2)
    private Scanner leitor = new Scanner(System.in);
    
    public Jogo() {
        this.jogada = new ArrayList<Jogada>(); 
        setEstado(1);
        setTurnoJogador(1); //peças brancas começam

        System.out.println("Jogador 1, por favor informe seu nome");
        System.out.print("Nome: ");
        String nome1 = leitor.nextLine();
        System.out.println("" + nome1 + ", voce eh o jogador 1. Suas pecas sao as brancas");
        this.jogadores[0] = new Jogador(nome1, "Branco");
        
        System.out.println("");
        System.out.println("Jogador 2, por favor informe seu nome");
        System.out.print("Nome: ");
        String nome2 = leitor.nextLine();
        System.out.println("" + nome2 + ", voce eh o jogador 2. Suas pecas sao as pretas");
        this.jogadores[1] = new Jogador(nome2, "Preto");
        inicializarJogo();
        
    }
    
    private void inicializarJogo() {
        this.tabuleiro = new Tabuleiro();
        
        pecas[0] = new Torre("Branco"); 
        pecas[1] = new Cavalo("Branco");
        pecas[2] = new Bispo("Branco");
        pecas[3] = new Dama("Branco");
        pecas[4] = new Rei("Branco");
        pecas[5] = new Bispo("Branco");
        pecas[6] = new Cavalo("Branco");
        pecas[7] = new Torre("Branco");
        
        for(int i = 8; i < 16; i++) { 
            pecas[i] = new Peao("Branco");
        } //criando todas as peças brancas
        
        pecas[16] = new Torre("Preto"); 
        pecas[17] = new Cavalo("Preto");
        pecas[18] = new Bispo("Preto");
        pecas[19] = new Dama("Preto");
        pecas[20] = new Rei("Preto");
        pecas[21] = new Bispo("Preto");
        pecas[22] = new Cavalo("Preto");
        pecas[23] = new Torre("Preto");
        
        for(int i = 24; i < 32; i++) { 
            pecas[i] = new Peao("Preto");
        } //criando todas as peças pretas
        
        //agr vamos entregar as peças brancas pro jogador 1
        for(int i = 0; i < 16; i++) {
            jogadores[0].receberPecas(pecas[i]);
        } 
        
        //pretas pro jogador 2
        for(int i = 16; i < 32; i++) {
            jogadores[1].receberPecas(pecas[i]);
        }
        
        //faltar colocar elas no tabuleiro
        for(int i = 0; i < 8; i++) {
            tabuleiro.ocuparCasa(1, i, pecas[i]);
            tabuleiro.ocuparCasa(2, i, pecas[i + 8]);
            tabuleiro.ocuparCasa(8, i, pecas[i + 16]);
            tabuleiro.ocuparCasa(7, i, pecas[i + 24]);
        }
            
    }
    
    public void setEstado(int estado){
        this.estado = estado;
    }
    
    public void setTurnoJogador(int jogador){
        this.turnoJogador = jogador;
    }
    
    public void realizarJogada(int linhaO, char colunaO, int linhaD, char colunaD) {
        
        Jogada novaJogada = new Jogada(linhaO, colunaO, linhaD, colunaD, jogadores[turnoJogador - 1], tabuleiro);

        if(jogadaValida(novaJogada)) {
            jogada.add(novaJogada); //adiciona no histórico
            
           Peca pecaMovendo = tabuleiro.getCasa(linhaO, colunaO).getPeca(); //pegando a peça
           tabuleiro.getCasa(linhaO, colunaO).desocupar(); //liberando a casa
           
           if(tabuleiro.getCasa(linhaD, colunaD).estaOcupada()) { //se for um moviemnto de captura
                if(turnoJogador == 1) {
                    jogadores[1].capturarPeca(tabuleiro.getCasa(linhaD, colunaD).getPeca());  //captura a peça do advr
                } else {
                    jogadores[0].capturarPeca(tabuleiro.getCasa(linhaD, colunaD).getPeca()); 
                }
           }
           
           tabuleiro.getCasa(linhaD, colunaD).ocupar(pecaMovendo); //ocupa a nova casa
        }
        
        //agr que a jogada foi feita, precisamos atualizar as info do jogo e mostrar as informaçoes na tela
        
        jogadores[1].mostrarCapturadas();
        tabuleiro.desenho();
        jogadores[0].mostrarCapturadas(); //printando o tabuleiro e as peças capturadas no lado de cada um 
        
        atualizandoStatus(novaJogada); //testa xeque e xeque mate e imprime uma mensagem dependendo
        
        if(turnoJogador == 1) {
            setTurnoJogador(2);
        } else {
            setTurnoJogador(1);
        } //troca o turno
    } 
    
    public boolean jogadaValida(Jogada novaJogada) {        
        if(novaJogada.ehValida()) return true;
        
        return false;
    }
    
    private void atualizandoStatus(Jogada novaJogada) {
        
        if(turnoJogador == 1) {
            if(novaJogada.ehXeque(jogadores[1])) estado = 2;
            if(novaJogada.ehXequeMate(jogadores[1])) estado = 3;
            
            if(estado == 2) System.out.println("O rei Preto esta em Xeque!");
            if(estado == 3) System.out.println("O rei Preto esta em Xeque Mate!");
        } else {
            if(novaJogada.ehXeque(jogadores[0])) estado = 2;
            if(novaJogada.ehXequeMate(jogadores[0])) estado = 3;
        
            if(estado == 2) System.out.println("O rei Branco esta em Xeque!");
            if(estado == 3) System.out.println("O rei Branco esta em Xeque Mate!");
        } //troca o turno
    }
    
    //parece completo, acho que gerenciador é que vai coordenar o jogo, será?
    
}
