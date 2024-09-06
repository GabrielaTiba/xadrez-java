package xadrez;

public class Rei extends Peca {
    
    public Rei(String cor, boolean estado) {
        super(cor, estado);
    }
    
    @Override 
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (capturada) { //se a peça ainda está no jogo
            
            int dLinha = Math.abs(linhaO - linhaD);
            int dColuna = Math.abs(colunaO - colunaD); //aparentemente, Math.abs deixa as contas em valor absoluto
            
            if(dLinha > 1 || dColuna > 1) return false;
            //se a diferença entre a linha/coluna de origem e de destino for maior que um , então o movimento do Rei é inválido
            
            return true;
        } 
        return false;
    }
    
    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "" + linhaO + colunaO + linhaD + colunaD;
        } //como o rei só se move uma casa mesmo, não precisa mais que isso
        
        return "";
    }
    
    @Override 
    public String desenho() {
        return this.cor.equals("branco") ? "♔" : "♚";
    }
}
