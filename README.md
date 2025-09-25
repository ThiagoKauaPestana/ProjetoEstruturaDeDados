# Projeto Estrutura De Dados


NOME: Thiago Kauã Pestana do Amaral  
RA: 10420686  
NOME: Jean Alex da Silva  
RA: 10426728  
NOME: Moisés Manoel Dos Santos Filho  
RA: 10419955  
Professor: Thiago Graziani Traue  

## Objetivos de Aprendizagem:

Implementar estruturas de dados básicas do zero: pilha (LIFO) e lista linear (encadeada simples).
Manipular matriz como tabuleiro e vetores para ranking, inventário visual e placar de tesouros.
Aplicar busca (linear e binária) e ordenação (Insertion e Quick/Merge) no ranking.
Trabalhar I/O simples (arquivos .txt) e parâmetros de execução (seed, caminho do mapa).
Medir e argumentar sobre complexidade das operações.

## Escopo do Jogo

Carregamento de mapas a partir de arquivos de texto.
Movimentação do jogador em uma grade 2D.
Sistema de inventário de chaves baseado em uma pilha (LIFO).
Cálculo de pontuação e sistema de ranking persistente em arquivo CSV.


## Representação do Labirinto (Classe Board)

Decisão: Utilizar uma matriz de caracteres bidimensional (char[][]) para representar o mapa do jogo.
Justificativa: A matriz é a estrutura mais natural e intuitiva para representar uma grade 2D como um labirinto. Ela oferece acesso direto e extremamente rápido a qualquer célula do mapa através de seus índices (linha e coluna). A verificação de uma posição, como mapa[r][c], é uma operação de tempo constante O(1), o que garante que a movimentação e a renderização do mapa sejam altamente eficientes, independentemente do tamanho do labirinto.

## Inventário de Chaves (Classe Inventory)

Decisão: Utilizar uma matriz de caracteres bidimensional (char[][]) para representar o mapa do jogo.
Justificativa: A matriz é a estrutura mais natural e intuitiva para representar uma grade 2D como um labirinto. Ela oferece acesso direto e extremamente rápido a qualquer célula do mapa através de seus índices (linha e coluna). A verificação de uma posição, como mapa[r][c], é uma operação de tempo constante O(1), o que garante que a movimentação e a renderização do mapa sejam altamente eficientes, independentemente do tamanho do labirinto.

## Gerenciamento do Ranking (Classe RankingManager)
Decisão: Utilizar uma Lista Dinâmica (ArrayList<PlayerScore>) para armazenar as pontuações lidas do arquivo CSV antes de exibi-las.
Justificativa: Uma lista dinâmica é ideal para esta tarefa, pois o número de registros no ranking não é conhecido previamente. Ela permite adicionar novos scores de forma flexível. O ponto crucial é a necessidade de ordenação. Após carregar todos os dados para a lista, utilizamos o algoritmo de ordenação padrão do Java (List.sort()), que possui uma complexidade de tempo eficiente de O(n log n), onde n é o número de scores.


## Pseudocódigos e Análise de Complexidade (Big-O)

### Algoritmo: Carregamento do Mapa (MapLoader)
Descrição: Lê um arquivo de texto, interpreta suas dimensões e conteúdo, e popula a matriz que representa o tabuleiro.

- Pseudocódigo:  
<img width="569" height="207" alt="image" src="https://github.com/user-attachments/assets/77cf4555-dcb7-43a4-b414-bac65fae2fff" />


Análise de Complexidade (Big-O):  
- Tempo: O(R * C). O algoritmo precisa percorrer cada célula da matriz uma vez para preenchê-la. O tempo de execução cresce linearmente com o número de células no mapa.  
- Espaço: O(R * C). A complexidade de espaço é dominada pela necessidade de armazenar toda a matriz do mapa na memória.






### Algoritmo: Movimentação do Jogador (Game.movePlayer)

Descrição: Processa a entrada do usuário (w/a/s/d), calcula a nova posição e interage com a célula de destino.

- Pseudocódigo:  
<img width="636" height="235" alt="image" src="https://github.com/user-attachments/assets/67c3b164-840e-42d4-9a12-74fcaa684305" />  


Análise de Complexidade (Big-O):  
- Tempo: O(1). Cada movimento envolve um número fixo de operações (cálculo de coordenadas, acesso a uma posição da matriz, algumas comparações). O tempo para executar um movimento é constante e não depende do tamanho do mapa.  
- Espaço: O(1). Nenhuma nova estrutura de dados significativa é alocada para um movimento.  
