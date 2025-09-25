package labirinto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapLoader {

    public static Board loadFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado: " + path);
            return null;
        }

        try (Scanner sc = new Scanner(file)) {
            if (!sc.hasNextLine()) {
                System.out.println("Arquivo vazio: " + path);
                return null;
            }

            // primeira linha: linhas colunas
            String[] parts = sc.nextLine().trim().split("\\s+");
            if (parts.length < 2) {
                System.out.println("Formato inválido na primeira linha.");
                return null;
            }

            int rows, cols;
            try {
                rows = Integer.parseInt(parts[0]);
                cols = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Número de linhas ou colunas inválido.");
                return null;
            }

            char[][] mapa = new char[rows][cols];
            int startRow = -1, startCol = -1;

            for (int i = 0; i < rows; i++) {
                if (!sc.hasNextLine()) {
                    System.out.println("Faltam linhas no arquivo.");
                    return null;
                }

                String line = sc.nextLine();
                if (line.length() != cols) {
                    System.out.println("Linha " + (i+1) + " tem tamanho diferente de " + cols);
                    return null;
                }

                for (int j = 0; j < cols; j++) {
                    char ch = line.charAt(j);
                    mapa[i][j] = ch;

                    if (ch == 'S' || ch == 's') {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            if (startRow == -1 || startCol == -1) {
                System.out.println("Mapa não possui ponto inicial 'S'.");
                return null;
            }

            return new Board(mapa, startRow, startCol);

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return null;
        }
    }
}
