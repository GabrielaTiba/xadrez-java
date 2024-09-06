/*
        Não completa!!
        O jogo cria até 2 jogadores
        O jogo cria um tabuleiro
        O jogo cria uma peça
        O jogo cria uma ou mais jogadaas e verifica estas jogadas
*/

package xadrez;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogadores[] = new Jogador[2];
    private ArrayList<Jogada> jogada;
    private Peca pecas[] = new Peca[32];
    private int estado; // inicio 1, xeque 2, xeque-mate 3
    private int turnoJogador; // qual jogador é a vez branco(1) ou preto(2)
    
    public Jogo(){
        this.jogada = new ArrayList<Jogada>();
        setEstado(1);
        setTurnoJogador(1); // não sei quem que começa primeiro
        
        // pegando informações do jogador 1 
        System.out.println("Jogador 1, por favor informe seu nome e a cor das suas peças");
        Scanner leitor = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome1 = leitor.nextLine();
        System.out.print("Cor: ");
        String cor1 = leitor.nextLine();
        this.jogadores[0] = new Jogador(nome1, cor1);
        
        // pegando informações do jogador 2
        System.out.println("Jogador 1, por favor informe seu nome e a cor das suas peças");
        System.out.print("Nome: ");
        String nome2 = leitor.nextLine();
        System.out.print("Cor: ");
        String cor2 = leitor.nextLine();
        this.jogadores[0] = new Jogador(nome2, cor2);
    }
    
    public void setEstado(int estado){
        this.estado = estado;
    }
    
    public void setTurnoJogador(int jogador){
        this.turnoJogador = jogador;
    }
    
    public boolean jogadaValida(int linhaO, char colunaO, int linhaD,char colunaD){
        return this.jogadaValida(linhaO, colunaO, linhaD, colunaD);
    }
    
    
    
}
