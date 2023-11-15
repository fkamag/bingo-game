# Bingo Game

## TL;DL
Construir um jogo de bingo multiplayer para rodar local com opções para sorteio automático aleatório ou manual que ao final indique alguns dados estatísticos

## Game
### 01 - Etapa 1 - Apresentação e Jogadores
1. Mensagem de boas vindas e mostrar opções de comando
1. Dar um design bacana para o visual e dê um nome ao jogo
1. Considerar o modo multiplayer automático com 1 ou mais jogadores
1. Separar por hifen o nickname: player1-player2-player3

### 02 - Etapa 2 - Cartelas
1. Gerar as cartelas desejadas 
1. Opções de comando para cartelas geradas RANDOM ou MANUAL
1. Para MANUAL localizar o nickname preencher a cartela 
1. O input deverá ser: `"1,2,3,4,5-6,7,8,9,1-2,3,4,5,6"`
1. Para RANDOM será gerado automaticamente aleatórias cartelas não repetidas
1. A cartela não pode ter números repetidos
1. No dia do game iremos expor todas as cartelas como neste exemplo que fizemos no pad
  1. Veja que [aqui no pad](https://pad.riseup.net/p/1JDJ0JDs07YTO5qP8cGt) fizemos um ensaio manual. A idéia é que o programa gere as cartelas

### 03 - Etapa 3 - Números Sorteados
1. Opções de comando do sorteio podem ser RANDOM ou MANUAL
1. Para MANUAL os números sorteados entrarão via Scanner
1. O input deverá seguir a sintaxe: `"1,2,3,4,5"`
1. Para RANDOM serão números aleatórios não repetidos na cartela
1. A cada round deverá imprimir:
1. Um ranking dos top 3 mais bem pontuados no game
1. Um pedido de pressionar a tecla para continuar via Scanner
1. Se pressionar X aborta o game 

### 04 - Etapa 4 - Fim do Jogo
1. Cada jogador terá um array para indicar os números acertados
1. Esse array indica a posição de cada número na cartela
1. Aqui temos os dois ultimos números acertados, ex: `0,0,0,1,1`
1. O bingo será eleito quando o jogador tiver todos com número 1
1. Ao final do game exibir o ranking geral e estatísticas do game:
    - Quantidade de rounds
    - Cartela premiada com números ordenados e nome do vencedor
    - Quantidade e números sorteados em ordem
    - Ranking geral ordenado pelo número de acertos

### 05 - Etapa 5 - Regras Gerais e Sugestões para implementar
1. Não usar classes e derivadas de Collections, use array/matriz
1. No modo manual iremos anunciar as cartelas no pad.riseup
1. Daí cada um marca a sua cartela manualmente para acompanhar
1. Pode usar classes utilitárias do java.util como Random e Arrays 
1. Estruture seu código em métodos por responsabilidade
