package xadrez;

public abstract class Peca {
    protected String cor;
    protected boolean capturada;
    
    public Peca(String cor, boolean capturada){
        this.cor = cor;
        this.capturada = capturada;
    }
    
    // método abstrato para desenhar a peça na tela:
    public abstract String desenho();
    
    // método abstrato para verificar se o movimento é valido;
    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);
    
    // método abstrato para fornecer o caminho que a peça vai percorrer 
    public abstract String caminho(int linhaO, char ColunaO, int linhaD, char ColunaD);
    
    public String getCor(){
        return cor;
    }
    
    public boolean isCapturada(){
        return this.capturada;
    }
    
    public void setCapturada(boolean capturada){
        this.capturada = capturada;
    }
}
