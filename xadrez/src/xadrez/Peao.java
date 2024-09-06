package xadrez;

public class Peao extends Peca {
    
    public Peao(String cor, boolean estado) {
        super(cor, estado);
    }
    
    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (capturada) {
            
            int dLinha = linhaD - linhaO;
            int dColuna = colunaD - colunaO;
            
            if (dLinha == 1 && dColuna == 0) return true; //peão só pode se mover um casa para frente 
            
            if (dColuna == 0 && dLinha == 2 && linhaO == 2) return true; //salvo na primeira jogada, ele pode mover duas casas
            //eu deveria fazer o movimento de captura aqui também? 
            
        }
        
        return false;
    }
    
    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            
            StringBuilder percurso = new StringBuilder();
            percurso.append(linhaO).append(colunaO);
            
            if(linhaD - linhaO == 2) {
                percurso.append(++linhaO).append(colunaO);
                percurso.append(++linhaO).append(colunaO);
            } else {
                percurso.append(++linhaO).append(colunaO);
            }
            
            return percurso.toString();
        }
        
         return "";
    }
    
    @Override
    public String desenho() {
        return this.cor.equals("branco") ? "♙" : "♟";
    }
}
   
   
