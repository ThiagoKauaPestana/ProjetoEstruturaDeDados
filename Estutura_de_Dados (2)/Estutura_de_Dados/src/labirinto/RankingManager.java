package labirinto;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RankingManager {
    private static final String RANKING_FILE = "ranking.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Salvar score no ranking
    public static void saveScore(String playerName, int score) {
        try (FileWriter fw = new FileWriter(RANKING_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String timestamp = LocalDateTime.now().format(formatter);
            PlayerScore playerScore = new PlayerScore(playerName, score, timestamp);
            out.println(playerScore.toString());

        } catch (IOException e) {
            System.out.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    // Ler e exibir ranking ordenado por score
    public static void showRanking() {
        List<PlayerScore> rankings = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(RANKING_FILE))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data.length == 3) {
                    rankings.add(new PlayerScore(data[0], Integer.parseInt(data[1]), data[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum ranking encontrado. Seja o primeiro a jogar!");
            return;
        }

        // Ordenar por score (maior primeiro)
        rankings.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        // Exibir top 10
        System.out.println("\n=== TOP 10 RANKING ===");
        System.out.println("Posição | Jogador       | Pontuação | Data/Hora");
        System.out.println("--------|---------------|-----------|-------------------");

        for (int i = 0; i < Math.min(rankings.size(), 10); i++) {
            PlayerScore ps = rankings.get(i);
            System.out.printf("%-8d| %-13s | %-9d | %s%n",
                    i + 1, ps.getPlayerName(), ps.getScore(), ps.getTimestamp());
        }
    }

    // Limpar ranking (opcional)
    public static void clearRanking() {
        try {
            new FileWriter(RANKING_FILE, false).close();
            System.out.println("Ranking limpo!");
        } catch (IOException e) {
            System.out.println("Erro ao limpar ranking: " + e.getMessage());
        }
    }
}