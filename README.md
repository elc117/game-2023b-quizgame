
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
        
## Referências utilizadas no desenvolvimento

- Leitura do TXT do mapa e renderização inspirados em um vídeo sobre Jogo 3d feito em libgdx
   - Vídeo em questão: https://www.youtube.com/watch?v=0Sj6Ja8Gn5A
   - Repositório com geração do mapa do autor do vídeo: https://github.com/TheInvader360/arena-roamer/blob/master/arena-roamer/src/com/theinvader360/arenaroamer/World.java

- Assets retirados dos seguintes jogos:
   - Materiais de cena: Minecraft
   - Sprite do personagem: Pokemon Fire Red (GBA)
   - Fonte utilizada no jogo: Minecraft
   - Fundos de telas: edição e adaptção de materiais do Minecraft

- Função _updateTextBlink_ foi sugerida pelo ChatGPT e aprimorada conforme necessidade e testes posteriores

- Playlist de vídeos tutoriais assistidos pelo grupo para entendimento geral do funcionamento da libgdx: https://www.youtube.com/playlist?list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf

- Função _getTextWidth_ sugerida pelo ChatGPT e aprimorada conforme necessidade e testes posteriores

- Ideia geral de como renderizar diferentes telas foi concebida depois de dicas do aluno Luis Henrique Cardoso

- Tutorial sobre jogo em 3d feito com libgdx mencionado na apresentação do trabalho (utilizado para definir que não fariamos um jogo 3d): https://www.youtube.com/watch?v=e-3OMXY9bDU