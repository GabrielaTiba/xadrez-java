package xadrez;

public class Tabuleiro {
    
    private Casa[][] casas = new Casa[8][8]; //uma matriz que é o tabuleiro
    
    public Tabuleiro() {
        
        for (int i = 0; i < 8; i++) { //linha
            for (int j = 0; j < 8; j++) { //coluna
                
                char jChar = (char)(j + 97);
                casas[i][j] = new Casa(i + 1, jChar); //cria uma casa nova
                
                switch(i) {
                
                case 0 -> {
                    //se a linha for 1, vamos colocar as peças brancas
                    switch (jChar) {
                        case 'a', 'h' -> casas[i][j].ocupar(new Torre("branco", true));
                        case 'b', 'g' -> casas[i][j].ocupar(new Cavalo("branco", true));
                        case 'c', 'f' -> casas[i][j].ocupar(new Bispo("branco", true));
                        case 'd' -> casas[i][j].ocupar(new Dama("branco", true));
                        case 'e' -> casas[i][j].ocupar(new Rei("branco", true));
                        default -> {
                        }
                    }
                    }


                    
                case 7 -> {
                    //se a linha for 8, vamos botar as peças pretas
                    if(jChar == 'a' || jChar == 'h') casas[i][j].ocupar(new Torre("preto", true));
                    if(jChar == 'b' || jChar == 'g') casas[i][j].ocupar(new Cavalo("preto", true));
                    if(jChar == 'c' || jChar == 'f') casas[i][j].ocupar(new Bispo("preto", true));
                    if(jChar == 'd') casas[i][j].ocupar(new Dama("preto", true));
                    if(jChar == 'e') casas[i][j].ocupar(new Rei("preto", true));
                    }
                    
                case 1 -> //se a linha for 2, vamos botar os peões brancos
                    casas[i][j].ocupar(new Peao("branco", true));
                    
                case 6 -> //se for 7, os peões pretos
                    casas[i][j].ocupar(new Peao("preto", true));
                }
                
            }
            
        }
    } //disposição inicial do tabuleiro
    
    public void desenho() {
        for (int i = 7; i >= 0; i--) { 
            for (int j = 0; j < 8; j++) {
                System.out.print("" + casas[i][j].desenha());
            }
            System.out.println("");
        }
    }
    
    public boolean noLimite(Casa casa) {
        if(casa.getLinha() < 1 || casa.getLinha() > 8) return false; //linha é válido de 1 a 8
        if(casa.getColuna() < 'a' || casa.getColuna() > 'h') return false; //coluna de 'a' a 'h'
        return true;
    }
    
    public Peca getPeca(Casa posicao){
        return posicao.getPecaNaCasa();
    }
    
}
