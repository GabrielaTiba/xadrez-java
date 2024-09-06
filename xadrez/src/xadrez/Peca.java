package xadrez;

public abstract class Peca {
    
    protected String cor; //brancas => letra maiúscula, pretas => letra minúscula
    protected boolean estado; //true => em jogo, false => capturada
    
    public Peca(String cor) {
        this.cor = cor;
        estado = true;
    }
    
    
    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);
    public abstract String desenha();
    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);
    public abstract String tipo();
    
    public String getCor() {
        return cor;
    }
    
    public void capturar() {
        estado = false;
    } //a cor não vai mudar mas o estado sim, então ta aí
    
    
}
