package labirinto;

import java.io.File;
import java.io.InputStream; // Import necessário
import java.util.Scanner;

public class MapLoader {

    // --- NOVO MÉTODO ---
    // Este método carrega o mapa como um "recurso" de dentro do projeto
    public static Board loadFromResource(String resourcePath) {
        // Tenta encontrar o recurso no projeto
        InputStream inputStream = MapLoader.class.getResourceAsStream(resourcePath);

        // Se não encontrar, o recurso não existe.
        if (inputStream == null) {
            System.out.println("Recurso não encontrado: " + resourcePath);
            return null;
        }

        // A partir daqui, a lógica é a mesma de antes, mas usando o "inputStream"
        try (Scanner sc = new Scanner(inputStream)) {
            if (!sc.hasNextLine()) {
                System.out.println("Arquivo de recurso vazio: " + resourcePath);
                return null;
            }
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
                    System.out.println("Linha " + (i + 1) + " tem tamanho diferente de " + cols);
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
        }
    }

    // O método antigo pode continuar aqui, não atrapalha em nada.
    public static Board loadFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado: " + path);
            return null;
        }
        // ... (resto do método antigo)
        return null; // Apenas para compilar, já que não estamos usando
    }
}