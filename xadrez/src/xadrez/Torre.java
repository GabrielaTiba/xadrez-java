package xadrez;

public class Torre extends Peca {
    
    public Torre(String cor, boolean estado) {
        super(cor, estado);
    }
    
    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(capturada) { 
            
            if(linhaO == linhaD || colunaO == colunaD) return true;
            //se a linha ou coluna de destino for igual a de origem, então ele esta se movendo em linha reta
        }
        
        return false;
    }
    
    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            
            StringBuilder percurso = new StringBuilder();
            percurso.append(linhaO).append(colunaO);
            
            if(linhaO == linhaD) {
                if(colunaO < colunaD) {
                    while(colunaO != colunaD) percurso.append(linhaO).append(++colunaO);
                } else {
                    while(colunaO != colunaD) percurso.append(linhaO).append(--colunaO);
                }
            } else {
                if(linhaO < linhaD) {
                    while(linhaO != linhaD) percurso.append(++linhaO).append(colunaO);
                } else {
                    while(linhaO != linhaD) percurso.append(--linhaO).append(colunaO);
                }
            }
            
            return percurso.toString();
        }
        
        return "";
    }
    
    @Override
    public String desenho() {
        return this.cor.equals("branco") ? "♖" : "♜";
    }
    
}
