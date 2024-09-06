package xadrez;

public class Bispo extends Peca {
    
    public Bispo (String cor, boolean estado) {
        super(cor, estado);
    }
    
    @Override 
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (capturada) {
            
            int dLinha = Math.abs(linhaO - linhaD);
            int dColuna = Math.abs(colunaO - colunaD);
            
            if(dLinha != dColuna) return false; //o bispo só pode se mover na diagonal, então a única maneira de se mover
            //é se a distancia andada na linha e na coluna for igual
            return true;
        }
        
        return false;
    }
    
    @Override 
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            
            StringBuilder percurso = new StringBuilder();
            percurso.append(linhaO).append(colunaO);
            
            while(linhaO != linhaD || colunaO != colunaD) {
                if(linhaO < linhaD) {
                    percurso.append(++linhaO);
                } else {
                    percurso.append(--linhaO);
                }
                
                if(colunaO < colunaD) {
                    percurso.append(++colunaO);
                } else {
                    percurso.append(--colunaO);
                }
            }
            
            return percurso.toString();
        }
        
        return "";
    }
    
    @Override
    public String desenho() {
       return this.cor.equals("branco") ? "♗" : "♝";
    }
    
}
