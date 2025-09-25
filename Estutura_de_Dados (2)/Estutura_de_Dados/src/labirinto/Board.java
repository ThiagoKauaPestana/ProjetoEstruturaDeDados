package labirinto;

public class Board {
    char[][] mapa;
    int startRow, startCol;

    public Board(char[][] mapa, int startRow, int startCol) {
        this.mapa = mapa;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public char getCell(int r, int c) { return mapa[r][c]; }
    public void setCell(int r, int c, char ch) { mapa[r][c] = ch; }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {}
    }

    public void render(Player p) {
        clearScreen();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (i == p.r && j == p.c)
                    System.out.print('⭐');
                else
                    System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
        System.out.println("Pontuação: " + p.score + " | Chaves: " + p.inventario);
        System.out.println("--------------------------------");
    }
}
