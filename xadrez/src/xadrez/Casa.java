package xadrez;

public class Casa {
    
    private String cor;
    private boolean ocupada;
    private int linha;
    private char coluna;
    private Peca pecaNaCasa;
    
    public Casa(int linha, char coluna) { //toda casa tem uma linha e uma coluna
        this.linha = linha;
        this.coluna = coluna;
        ocupada = false; //inicialmente ela está vazia
        pecaNaCasa = null; //não há peças nela
        
        if(linha%2 == 0 && coluna%2 == 0 || linha%2 != 0 && coluna%2 != 0) {
            cor = "preto"; //casas com linhas e colunas ímpares ou linhas e colunas pares são pretas
        } else {
            cor = "branco"; //par com ímpar é branco
        }
    }
    
    public void ocupar(Peca peca) {
        ocupada = true;
        pecaNaCasa = peca; //se a casa for ser ocupada, diga que peça vai pra lá
    }
    
    public void desocupar() {
        ocupada = false;
        pecaNaCasa = null;
    } //se a peça for sair
    
    public String desenha() {
        if(ocupada) {
           return pecaNaCasa.desenho() + " ";
        } 
        
        return "[]";
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Peca getPecaNaCasa() {
        return pecaNaCasa;
    }

    public void setPecaNaCasa(Peca pecaNaCasa) {
        this.pecaNaCasa = pecaNaCasa;
    }
    
    public int getLinha(){
        return this.linha;
    }
    
    public char getColuna(){
        return this.coluna;
    }
    
}
