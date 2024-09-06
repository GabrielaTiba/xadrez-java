package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private String nome; // nome do jogador
    private String cor; // cor das peças: "branco" ou "preto"
    private List<Peca> pecasAtivas; // peças ainda em jogo
    private List<Peca> pecasCapturadas; // peças que foram capturadas

    // construtor
    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.pecasAtivas = new ArrayList<>();
        this.pecasCapturadas = new ArrayList<>();
    }
    
    public void receberPecas(Peca p) {
        pecasAtivas.add(p);
    }
    
    public boolean ehDoJogador(Peca p) {
        if(pecasAtivas.contains(p)) return true;
        return false;
    }

    // solicita a jogada do jogador
    public String informaJogada() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(nome + ", digite sua jogada (ou 'parar' para interromper): ");
        return scanner.nextLine();
    }

    // remove a peça especificada da lista de peças ativas e adiciona à lista de peças capturadas
    public void capturarPeca(Peca peca) {
        if (pecasAtivas.remove(peca)) {
            pecasCapturadas.add(peca);
            peca.capturar();
        }
    }

    public String getNome() {
        return nome;
    }
    
    public String getCor() {
        return cor;
    }
    
    public void mostrarCapturadas() {
        if (!pecasCapturadas.isEmpty()) {
            for (Peca peca : pecasCapturadas) {
                System.out.print(peca.desenha() + " "); 
            }
            System.out.println("");
        }
    }
}
