/*
    A jogada: 
    - conhece o tabuleiro 
    - cria um caminho (composição) -> não sei como 
    - tem um jogador (agregação)

*/
package xadrez;

public class Jogada {
  private Jogador jogador; // jogador que está realizando a jogada
  private Tabuleiro tabuleiro;
  private Caminho caminho; // caminho que a peça percorre da posição inicial até a posição final

  // uma vez que a jogada é criada, ela não pode ser alterada
  public Jogada(Jogador jogador, int linhaO, char colunaO, int linhaD, char colunaD) {
    /*
        a posicao inicial e a posicao final da jogada são casas no tabuleiro
        Esse tabuleiro não é criado na classe jogada, a classe jogada apenas conhece o tabuleiro
        O caminho é CRIADO dentro da casa jogada a partir das casas informadas para o tabuleiro
        A jogada recebe um jogador já existente (não cria a classe jogador)
     */
    
  }

  // verifica se a jogada é válida
  public boolean ehValida(Tabuleiro tabuleiro) {
    // verifica se as posições estão dentro dos limites do tabuleiro
    if (!tabuleiro.noLimite(posicaoInicial) || !tabuleiro.noLimite(posicaoFinal)) {
      return false;
    }

    // verifica se a peça na posição inicial pertence ao jogador
    Peca pecaInicial = tabuleiro.getPeca(posicaoInicial);
    if (pecaInicial == null || !pecaInicial.getCor().equals(jogador.getCor())) {
      return false;
    }

    // verifica se a posição final está livre ou ocupada por uma peça do oponente
    Peca pecaFinal = tabuleiro.getPeca(posicaoFinal);
    if (pecaFinal != null && pecaFinal.getCor().equals(jogador.getCor())) {
      return false;
    }

    // verifica se o caminho está livre, caso a peça não possa pular
    if (!pecaInicial.podePular() && !tabuleiro.caminhoEstaLivre(caminho)) {
      return false;
    }

    // verifica se o movimento é válido para a peça
    return pecaInicial.movimentoValido(posicaoInicial.getLinha(), posicaoInicial.getColuna(), posicaoFinal.getLinha(), posicaoFinal.getColuna());
  }

  // verifica se a jogada resulta em xeque
  public boolean ehXeque(Tabuleiro tabuleiro) {
    // simula a jogada e verifica se o rei adversário está em xeque
    tabuleiro.simulaJogada(posicaoInicial, posicaoFinal);
    boolean emXeque = tabuleiro.reiEmXeque(jogador.oponente().getCor());
    // depois de verificar, desfaz a jogada e retorna o resultado 'true' se a jogada resulta em xeque ou 'false' caso contrário
    tabuleiro.desfazSimulacao();
    return emXeque;
  }

  // verifica se a jogada resulta em xeque-mate
  public boolean ehXequeMate(Tabuleiro tabuleiro) {
    // simula a jogada e verifica se o rei adversário está em xeque-mate
    // primeiro verifica se a jogada resulta em xeque. Se não for xeque, a jogada não resulta em xeque-mate, então retorna 'false'
    if (ehXeque(tabuleiro)) { 
      return !tabuleiro.temMovimentosValidos(jogador.oponente().getCor());
    }
    return false;
  }

  // Getters para as propriedades da jogada
  public Jogador getJogador() {
    return jogador;
  }

  public Casa getPosicaoInicial() {
    return posicaoInicial;
  }

  public Casa getPosicaoFinal() {
    return posicaoFinal;
  }

  public Peca getCaminho() {
    return caminho;
  }
}
