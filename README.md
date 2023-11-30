
# Maze game 

## Jogo:

   Em MazeGame, voce esta em um labirinto, dentro de uma caverna. Para progredir no jogo, e necessario encontrar as esmeraldas, que ao sofrerem interacoes(tecla ENTER), permitem que o jogador tente responder a algumas perguntas, e entao avancar no jogo. 

   O jogador se move com as teclas WASD.
   
   Cada fase possui uma questao sobre paleontologia, especificamente no cenario gaucho. 
   
   Cada resposta correta contabiliza 1 ponto, e o placar pode ser visto ao final do jogo.

## Fases e perguntas

As fases e perguntas de MazeGame podem ser facilmente construidas atraves de arquivos .txt.

Os mapas podem ser construidos atraves de 4 caracteres, sendo eles W, #, V e W.

W = parede

'#' = spawnpoint

V = void(define o limite de renderizacao do mapa)

C = objetivo(esmeralda)

As perguntas sao divididas no arquivo .txt pelo simbolo ';' sendo que a primeira parte da linha no .txt refere-se ao enunciado, a parte 2-3-4 dizem respeito as opcoes que o jogador tem, e a ultima, sobre resposta da pergunta, que pode variar de 1 ate 3, dependendo se ela for A(1), B(2) ou C(3).
        