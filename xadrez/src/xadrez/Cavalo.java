package xadrez;

/**
 *
 * @author bianca
 */
public class Cavalo extends Peca {

    public Cavalo(String cor, boolean estado) {
        super(cor, estado);
    }

    @Override
    public String desenho() {
        return this.cor.equals("branco") ? "♘" : "♞";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int linha = Math.abs(linhaD-linhaO);
        int coluna = Math.abs(colunaD-colunaO);
        
        return(linha == 2 && coluna == 1) || (linha == 1 && coluna == 2);
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if(movimentoValido(linhaO, colunaO, linhaD, colunaD)) { //esse foi desgraçado hein
            
            StringBuilder percurso = new StringBuilder();
            boolean flag = false;
            
            percurso.append(linhaO).append(colunaO);
            
            if (linhaD - linhaO == -1) {
                percurso.append(--linhaO).append(colunaO); 
                flag = true;
            } else if (linhaD - linhaO == 1) {
                percurso.append(++linhaO).append(colunaO);
                flag = true;
            } else if (colunaD - colunaO == -1) {
                percurso.append(linhaO).append(--colunaO);
            } else if (colunaD - colunaO == 1) {
                percurso.append(linhaO).append(++colunaO);
            }
            
            if(flag) {
                if(colunaO < colunaD) {
                    percurso.append(linhaO).append(++colunaO);
                    percurso.append(linhaO).append(++colunaO);
                } else {
                    percurso.append(linhaO).append(--colunaO);
                    percurso.append(linhaO).append(--colunaO);
                }
                
            } else {
                if(linhaO < linhaD) {
                    percurso.append(++linhaO).append(colunaO);
                    percurso.append(++linhaO).append(colunaO);
                } else {
                    percurso.append(--linhaO).append(colunaO);
                    percurso.append(--linhaO).append(colunaO);
                }
            } //nao consegui pensar num jeito inteligente e tive que fazer tudo separado
            
            return percurso.toString();
        } 
        
        return "";
    }
    
}
