package labirinto;

import java.util.Random;

public class Game {
    Board board;
    Player player;
    Random rand;

    // Variáveis para rastrear detalhes da pontuação
    private int movimentos;
    private int tesourosColetados;
    private int armadilhasAtivadas;
    private int portasAbertas;
    private int chavesColetadas;

    // Novas variáveis para controlar objetivos totais
    private int totalTesouros;
    private int totalChaves;
    private boolean jogoConcluido;

    public Game(String mapPath) {
        board = MapLoader.loadFromFile(mapPath);
        if (board == null) {
            System.out.println("Falha ao carregar mapa!");
            System.exit(1);
        }
        player = new Player(board.startRow, board.startCol, 5);
        rand = new Random();

        // Inicializar contadores
        this.movimentos = 0;
        this.tesourosColetados = 0;
        this.armadilhasAtivadas = 0;
        this.portasAbertas = 0;
        this.chavesColetadas = 0;
        this.jogoConcluido = false;

        // Contar objetivos totais no mapa
        contarObjetivos();
    }

    private void contarObjetivos() {
        totalTesouros = 0;
        totalChaves = 0;

        for (int i = 0; i < board.mapa.length; i++) {
            for (int j = 0; j < board.mapa[0].length; j++) {
                char cell = board.getCell(i, j);
                if (cell == '$') {
                    totalTesouros++;
                } else if (cell >= 'a' && cell <= 'z') {
                    totalChaves++;
                }
            }
        }

        System.out.println("Objetivos do mapa: " + totalTesouros + " tesouros e " + totalChaves + " chaves para coletar");
        System.out.println("⚠️  Lembre-se: Você precisa usar as chaves nas portas correspondentes!");
    }

    private boolean verificarVitoria() {
        // VERÃO 1: Só termina quando coletar TUDO E usar todas as chaves nas portas
        boolean coletouTodosTesouros = tesourosColetados >= totalTesouros;
        boolean coletouTodasChaves = chavesColetadas >= totalChaves;
        boolean usouTodasChaves = player.inventario.size() == 0; // Inventário vazio = todas chaves usadas

        return coletouTodosTesouros && coletouTodasChaves && usouTodasChaves;
    }

    public boolean isJogoConcluido() {
        return jogoConcluido;
    }

    public void movePlayer(char move) {
        if (jogoConcluido) return; // Não permite movimentos se o jogo acabou

        int newR = player.r;
        int newC = player.c;

        switch (move) {
            case 'w': newR--; break;
            case 's': newR++; break;
            case 'a': newC--; break;
            case 'd': newC++; break;
            default: return;
        }

        char nextCell = board.getCell(newR, newC);
        if (nextCell == '#') return;

        player.r = newR;
        player.c = newC;
        player.score -= 1;
        movimentos++;

        // --- ARMADILHA ---
        if (nextCell == 'T') {
            player.score -= 20;
            board.setCell(newR, newC, '.');
            armadilhasAtivadas++;
        }

        // --- PORTA ---
        else if (nextCell >= 'A' && nextCell <= 'Z') {
            char topo = player.inventario.peek() != null ? player.inventario.peek() : ' ';
            if (Character.toLowerCase(nextCell) == topo) {
                player.inventario.pop();
                player.score += 15;
                board.setCell(newR, newC, '.');
                portasAbertas++;
                System.out.println("🔓 Porta " + nextCell + " aberta! +15 pontos");
            }
        }

        // --- CHAVE ---
        else if (nextCell >= 'a' && nextCell <= 'z') {
            if (player.inventario.push(nextCell)) {
                board.setCell(newR, newC, '.');
                chavesColetadas++;
                System.out.println("🔑 Chave " + nextCell + " coletada!");
            }
        }

        // --- TESOURO ---
        else if (nextCell == '$') {
            int valor = rand.nextInt(41) + 10;
            player.score += valor;
            board.setCell(newR, newC, '.');
            tesourosColetados++;
            System.out.println("💰 Tesouro encontrado! +" + valor + " pontos");
        }

        // --- SAÍDA ---
        else if (nextCell == 'E') {
            player.score += 100;
        }

        // VERIFICAR SE GANHOU O JOGO (APÓS A AÇÃO)
        if (verificarVitoria()) {
            jogoConcluido = true;
            player.score += 100; // Bônus maior por completar missão difícil
            System.out.println("\n🎉 PARABÉNS! VOCÊ CONCLUIU A MISSÃO COMPLETA!");
            System.out.println("🏆 Bônus de conclusão: +100 pontos!");
            System.out.println("⭐ Coletou todos os tesouros e usou todas as chaves!");
        }
    }

    public void gerarResumo(String playerName) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 RESUMO DA PARTIDA - " + playerName.toUpperCase());
        System.out.println("=".repeat(50));

        System.out.printf("🏆 Pontuação Final: %d pontos%n", player.score);
        System.out.printf("👣 Movimentos Realizados: %d%n", movimentos);
        System.out.printf("💰 Tesouros Coletados: %d/%d%n", tesourosColetados, totalTesouros);
        System.out.printf("🔑 Chaves Coletadas: %d/%d%n", chavesColetadas, totalChaves);
        System.out.printf("🚪 Portas Abertas: %d/%d%n", portasAbertas, totalChaves); // Cada chave tem uma porta
        System.out.printf("💥 Armadilhas Ativadas: %d%n", armadilhasAtivadas);
        System.out.printf("🎒 Chaves no Inventário: %d%n", player.inventario.size());
        System.out.printf("⭐ Bônus por Chaves Restantes: +%d pontos%n", 5 * player.inventario.size());

        // Status da missão
        if (verificarVitoria()) {
            System.out.println("🎯 STATUS: MISSÃO COMPLETAMENTE CUMPRIDA! 🌟");
        } else {
            System.out.println("🎯 STATUS: MISSÃO INCOMPLETA");
            System.out.println("   - Faltam tesouros: " + (totalTesouros - tesourosColetados));
            System.out.println("   - Faltam chaves: " + (totalChaves - chavesColetadas));
            System.out.println("   - Chaves não usadas: " + player.inventario.size());
        }

        System.out.println("=".repeat(50));
    }

    public void bonusFinal() {
        int bonus = 5 * player.inventario.size();
        player.score += bonus;
        if (bonus > 0) {
            System.out.printf("⭐ Bônus final: +%d pontos (%d chaves no inventário)%n",
                    bonus, player.inventario.size());
        }
    }

    public void render() {
        board.render(player);
    }
}