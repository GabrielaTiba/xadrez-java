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
        inicializarPecas();
    }

    // preenche a lista de peças ativas com as peças iniciais do jogador
    // torre posicionada nas extremidades do tabuleiro
    // cavalo ao lado da torre
    // bispo ao lado do cavalo
    // rainha na casa central que corresponde à sua cor, ao lado do bispo
    // rei ao lado da rainha
    // oito peões posicionados na frente de todas essas peças
    private void inicializarPecas() {
        if (cor.equals("branco")) {
            pecasAtivas.add(new Torre("branco", false));
            pecasAtivas.add(new Cavalo("branco", false));
            pecasAtivas.add(new Bispo("branco", false));
            pecasAtivas.add(new Dama("branco", false));
            pecasAtivas.add(new Rei("branco", false));
            pecasAtivas.add(new Bispo("branco", false));
            pecasAtivas.add(new Cavalo("branco", false));
            pecasAtivas.add(new Torre("branco", false));
            for (int i = 0; i < 8; i++) {
                pecasAtivas.add(new Peao("branco", false));
            }
        } else {
            pecasAtivas.add(new Torre("preto", false));
            pecasAtivas.add(new Cavalo("preto", false));
            pecasAtivas.add(new Bispo("preto", false));
            pecasAtivas.add(new Dama("preto", false));
            pecasAtivas.add(new Rei("preto", false));
            pecasAtivas.add(new Bispo("preto", false));
            pecasAtivas.add(new Cavalo("preto", false));
            pecasAtivas.add(new Torre("preto", false));
            for (int i = 0; i < 8; i++) {
                pecasAtivas.add(new Peao("preto", false));
            }
        }
    }

    // solicita a jogada do jogador
    public String informaJogada() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(nome + ", digite sua jogada (ou 'parar' para interromper): ");
        return scanner.nextLine();
    }

    // retorna uma string com as peças capturadas
    public List<Peca> pecasCapturadas() {
        return pecasCapturadas;
    }

    // remove a peça especificada da lista de peças ativas e adiciona à lista de peças capturadas
    public void capturarPeca(Peca peca) {
        if (pecasAtivas.remove(peca)) {
            pecasCapturadas.add(peca);
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<Peca> getPecasAtivas() {
        return pecasAtivas;
    }

    public List<Peca> getPecasCapturadas() {
        return pecasCapturadas;
    }
}
