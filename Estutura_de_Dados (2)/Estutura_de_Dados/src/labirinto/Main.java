package labirinto;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String playerName = sc.nextLine().trim();
        if (playerName.isEmpty()) playerName = "Jogador";

        System.out.println("Escolha o mapa (1, 2 ou 3): ");
        String escolha = sc.nextLine();
        String mapFile = "src/Map/map" + escolha + ".txt";

        Game game = new Game(mapFile);
        boolean finished = false;

        while (!finished && !game.isJogoConcluido()) { // Verifica se o jogo acabou
            game.render();
            System.out.print("Movimento (w/a/s/d, q para sair): ");
            String line = sc.nextLine();
            if (line.isEmpty()) continue;

            char move = line.charAt(0);
            if (move == 'q') finished = true;
            else game.movePlayer(move);

            if (game.isJogoConcluido()) {
                finished = true;
            }
        }

        game.bonusFinal();
        game.render();
        game.gerarResumo(playerName);

        if (game.isJogoConcluido()) {
            System.out.println("🌟 MISSÃO CUMPRIDA! Você é um verdadeiro explorador!");
        }

        RankingManager.saveScore(playerName, game.player.score);

        System.out.println("\nDeseja ver o ranking completo? (s/n)");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            RankingManager.showRanking();
        }
        System.out.println("Obrigado por jogar! 👋");
    }
}