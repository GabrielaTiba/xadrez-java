package xadrez;

import java.util.ArrayList;
import java.util.List;

public class Caminho {
    private List<Casa> casas;
    private Casa casaInicial;
    private Casa casaFinal; //casa incial e final sao separadas
    
    public Caminho(Casa inicio, Casa fim) {
        casaInicial = inicio;
        casaFinal = fim;
        casas = new ArrayList<>();
    }

    public void adicionarCasa(Casa casa) {
        casas.add(casa);
    } //colocando casa no caminho
    
    public Casa getCasaInicial() {
        return casaInicial;
    }
    
    public Casa getCasaFinal() {
        return casaFinal;
    }
    
    public boolean estaLivre() {
        if(casas.isEmpty()) return true; //se nao existe caminho entre, liberado automaticamente
        
            for(Casa c: casas) {
                if(c.estaOcupada()) return false;
            } //se algum das casas no caminho estiver ocupada
        
            return true;
    } 

    
}
