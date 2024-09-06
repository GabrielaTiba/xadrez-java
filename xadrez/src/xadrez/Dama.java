package xadrez;

public class Dama extends Peca {
    
    public Dama(String cor, boolean estado) {
        super(cor, estado);
    }
    
    @Override 
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(capturada) {
            
            int dLinha = Math.abs(linhaO - linhaD);
            int dColuna = Math.abs(colunaO - colunaD);
            
            if(dLinha != dColuna && linhaO != linhaD && colunaO != colunaD) return false; //a dama pode se mover quantas casas quiser, pra onde quiser
            //mas temos que checar a diagonal
            
            return true;
            
        }
        return false;
    }
    
    @Override 
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            
            StringBuilder percurso = new StringBuilder(); //isso é concatenar String
            percurso.append(linhaO).append(colunaO); //colocando a origem
            
            while(linhaO != linhaD || colunaO != colunaD) { //enquanto não chegar na posição final tanto na linha qaunto na coluna
                if (linhaO < linhaD) {
                    percurso.append(++linhaO); //se a origem for menor que o destino, incrementa até se igualarem
                } else if (linhaO > linhaD) {
                    percurso.append(--linhaO); //se a origem for maior, diminui até ficar igual
                } else {
                    percurso.append(linhaO); //se já forem iguais, repete
                }
                
                if (colunaO < colunaD) {
                    percurso.append(++colunaO); //mesma lógica pra coluna
                } else if (colunaO > colunaD) {
                    percurso.append(--colunaO);
                } else {
                    percurso.append(colunaO);
                }
                
                
            }
            
            return percurso.toString(); //transforma de volta pra String normal
        }
        
        return "";
    }
    
    @Override 
    public String desenho() {
        return this.cor.equals("branco") ? "♕" : "♛";
    }

    
}
